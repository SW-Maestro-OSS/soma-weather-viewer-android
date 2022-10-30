package org.soma.weatherviewer.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase

class WeatherDetailInfoViewModel(
    private val weatherUseCase: WeatherUseCase,
    private val viewPagerIndex: Int
): ViewModel() {
    private var _weather = MutableStateFlow(WeatherModel.dummy())
    val weather: StateFlow<WeatherModel> get() = _weather

    init {
        getWeatherApi()
    }

    fun getWeatherApi() {
        viewModelScope.launch {
            val data = weatherUseCase.getFiveDaysWeather(37f, 127f)
            _weather.value = data[viewPagerIndex*8]
        }
    }
}