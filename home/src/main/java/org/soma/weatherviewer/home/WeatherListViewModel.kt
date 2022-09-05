package org.soma.weatherviewer.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.model.entity.Weather

class WeatherListViewModel: ViewModel() {
    // TODO: by Hilt
    val weatherModelList = MutableLiveData(listOf<Weather>())
}