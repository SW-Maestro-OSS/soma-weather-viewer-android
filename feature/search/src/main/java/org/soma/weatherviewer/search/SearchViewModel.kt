package org.soma.weatherviewer.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.usecase.GetCityWeatherUseCase
import org.soma.weatherviewer.domain.usecase.GetSearchCityNameUseCase
import org.soma.weatherviewer.domain.usecase.StoreSearchCityNameUseCase
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
	private val getCityWeatherUseCase: GetCityWeatherUseCase,
	private val getSearchCityNameUseCase: GetSearchCityNameUseCase,
	private val storeSearchCityNameUseCase: StoreSearchCityNameUseCase
) : ViewModel() {

	private val _searchCityWeatherFlow = MutableStateFlow(WeatherVO())
	val searchCityWeatherFlow = _searchCityWeatherFlow.asStateFlow()

	private val _searchCityNameFlow = MutableStateFlow("Seoul")
	val searchCityNameFlow = _searchCityNameFlow.asStateFlow()

	private val _toastMessage = MutableStateFlow<String?>(null)
	val toastMessage = _toastMessage.asStateFlow()

	init {
		fetchSearchCityName()
	}

	private fun fetchSearchCityName() {
		viewModelScope.launch {
			getSearchCityNameUseCase().collectLatest {
				_searchCityNameFlow.value = it
			}
		}
	}

	fun fetchCityWeather(cityName: String = "") {
		val city = cityName.ifEmpty { _searchCityNameFlow.value }
		viewModelScope.launch {
			getCityWeatherUseCase(
				city,
				onError = { message ->
					_toastMessage.value = message
				}
			).collectLatest {
				_searchCityWeatherFlow.value = it
			}
		}
	}

	fun setCityName(cityName: String) {
		viewModelScope.launch {
			storeSearchCityNameUseCase(
				cityName,
				onError = { message ->
					_toastMessage.value = message
				}
			).collectLatest {
				_searchCityNameFlow.value = cityName
				_searchCityWeatherFlow.value = it
			}
		}
	}

	fun clearToastMessage() {
		_toastMessage.value = null
	}
}