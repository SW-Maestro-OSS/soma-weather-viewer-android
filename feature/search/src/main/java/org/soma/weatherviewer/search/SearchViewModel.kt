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
import java.util.*
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

	fun fetchCityWeather(cityName: String = "", locale: Locale) {
		val city = cityName.ifEmpty { _searchCityNameFlow.value }
		viewModelScope.launch {
			getCityWeatherUseCase(
				city,
				locale,
				onError = { message ->
					_toastMessage.value = message
				}
			).collectLatest {
				_searchCityWeatherFlow.value = it
			}
		}
	}

	/**
	 * 사용자가 검색 시 cityName에 날씨정보를 호출하고 호출되지 않으면 존재하지 않는 city이므로
	 * DataStore에 저장하지 않습니다.
	 */
	fun setCityName(cityName: String, locale: Locale) {
		viewModelScope.launch {
			storeSearchCityNameUseCase(
				cityName,
				locale = locale,
				onError = { message ->
					_toastMessage.value = message
				}
			).collectLatest {
				_searchCityNameFlow.value = cityName
				_searchCityWeatherFlow.value = it
			}
		}
	}

	/**
	 * StateFlow는 기존 message와 동일하면 collect되지 않기 때문에 저장된 toastmessage를 지웁니다
	 */
	fun clearToastMessage() {
		_toastMessage.value = null
	}
}