package org.soma.weatherviewer.home.list

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
class WeatherListViewModel  @Inject constructor(
    private val weatherUseCase : WeatherUseCase
) : ViewModel() {
    private val _weatherModelList = MutableLiveData<List<WeatherModel>>()
    val weatherModelList : LiveData<List<WeatherModel>> = _weatherModelList

    init {
        getWeatherList()
    }

    private fun getWeatherList() {
        viewModelScope.launch {
            val data = weatherUseCase.getFiveDaysWeather(37f, 127f)
            _weatherModelList.postValue(data)
        }
    }
}