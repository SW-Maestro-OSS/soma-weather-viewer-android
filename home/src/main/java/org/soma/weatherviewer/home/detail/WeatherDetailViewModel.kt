package org.soma.weatherviewer.home.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.model.entity.Weather

class WeatherDetailViewModel: ViewModel() {
    // TODO: WeatherDetailRepository by Hilt
    var weather = MutableLiveData<Weather?>(null)

}