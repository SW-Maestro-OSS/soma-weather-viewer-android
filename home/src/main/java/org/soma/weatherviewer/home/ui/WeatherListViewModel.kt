package org.soma.weatherviewer.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.soma.weatherviewer.home.data.repository.WeatherListRepository
import org.soma.weatherviewer.home.domain.model.WeatherModel

class WeatherListViewModel: ViewModel() {
    private val weatherListDetail = WeatherListRepository()
    // TODO: by Hilt
    val weatherModelList = MutableLiveData(listOf<WeatherModel>())

    init {
        viewModelScope.launch {
            weatherModelList.value = weatherListDetail.readWeatherList(0.0f, 0.0f)
        }
    }
}