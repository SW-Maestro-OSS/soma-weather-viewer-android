package org.soma.weatherviewer.home.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase

class WeatherDetailInfoViewModel(
    private val weatherUseCase: WeatherUseCase
): ViewModel() {
    private var _weather = MutableStateFlow(listOf<WeatherModel>())
    val weather: StateFlow<List<WeatherModel>> get() = _weather

    init {
        getWeatherApi()
    }

    private fun getWeatherApi() {
        viewModelScope.launch {
            val data = weatherUseCase.getFiveDaysWeather(37f, 127f)
            _weather.value = data
        }
    }

}