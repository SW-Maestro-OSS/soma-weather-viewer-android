package org.soma.weatherviewer.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WeatherViewModel: ViewModel() {
    val viewStatus = MutableLiveData(0) // TODO: Enum으로 List/Detail 나누기

    fun switchViewStatus() {
        viewStatus.value?.let {
            viewStatus.value = (it + 1) % 2
        }
    }
}