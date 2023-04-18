package org.soma.weatherviewer.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.usecase.GetCityWeatherUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getCityWeatherUseCase: GetCityWeatherUseCase
) : ViewModel() {

	private val _searchCityWeatherFlow = MutableStateFlow(WeatherVO())
	val searchCityWeatherFlow = _searchCityWeatherFlow.asStateFlow()

	private val _cityNameFlow = MutableStateFlow("Seoul")
	val cityNameFlow = _cityNameFlow.asStateFlow()

	init {
		fetchCityWeather(cityNameFlow.value)
	}

	fun fetchCityWeather(
		cityName: String,
		units: WeatherTempUnits = WeatherTempUnits.CELSIUS
	) {
		viewModelScope.launch {
			getCityWeatherUseCase(cityName, units).collectLatest {
				_searchCityWeatherFlow.value = it
			}
		}
	}

	fun setCityName(cityName: String) {
		_cityNameFlow.value = cityName
		fetchCityWeather(cityName = cityName)
	}
}