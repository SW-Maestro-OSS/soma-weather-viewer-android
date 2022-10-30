package org.soma.weatherviewer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase
import org.soma.weatherviewer.common.util.HomeScreenOptionType
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherUseCase : WeatherUseCase,
    private val dataStoreUseCase: DataStoreUseCase
) : ViewModel() {

    private val _homeScreenOptionType = MutableStateFlow(HomeScreenOptionType.Current.name)
    val homeScreenOptionType: StateFlow<String> get() = _homeScreenOptionType

    private val _weatherList = MutableLiveData<List<WeatherModel>>()
    val weatherList : LiveData<List<WeatherModel>> = _weatherList

    val viewStatus = MutableLiveData(0) // TODO: Enum으로 List/Detail 나누기

    fun getHomeData() {
        viewModelScope.launch {
            _homeScreenOptionType.value = dataStoreUseCase.homeScreenOption.first()
            val data = weatherUseCase.getFiveDaysWeather(37f, 127f)
            _weatherList.postValue(data.subList(0,5))
        }
    }

    fun switchViewStatus() {
        viewStatus.value?.let {
            viewStatus.value = (it + 1) % 2
        }
    }

}