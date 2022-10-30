package org.soma.weatherviewer.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase : WeatherUseCase
) : ViewModel() {

    val viewStatus = MutableLiveData(0) // TODO: Enum으로 List/Detail 나누기

    fun switchViewStatus() {
        viewStatus.value?.let {
            viewStatus.value = (it + 1) % 2
        }
    }

    private val _weatherList = MutableLiveData<List<WeatherModel>>()
    val weatherList : LiveData<List<WeatherModel>> = _weatherList

    init {
        Log.d("WeatherViewModel", "call getWeatherAPI ")
        getWeatherApi()
    }

    private fun getWeatherApi() {
        viewModelScope.launch {
            val data = weatherUseCase.getFiveDaysWeather(37f, 127f)
            Log.d("WeatherViewModel", "getWeatherApi: ${data}")
            _weatherList.postValue(data.subList(0,5))
        }
    }
}