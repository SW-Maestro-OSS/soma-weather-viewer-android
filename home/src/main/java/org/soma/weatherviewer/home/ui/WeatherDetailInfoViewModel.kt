package org.soma.weatherviewer.home.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.soma.weatherviewer.home.data.repository.WeatherDetailRepository
import org.soma.weatherviewer.home.domain.model.WeatherModel

class WeatherDetailInfoViewModel: ViewModel() {
    private val weatherDetailRepository = WeatherDetailRepository()
    // TODO: WeatherDetailRepository by Hilt
    var weatherModel = MutableLiveData<WeatherModel?>(null)
    
    init {
        viewModelScope.launch {
            weatherModel.value = weatherDetailRepository.readWeatherDetail(10.1f, 10.5f)
        }
    }
}