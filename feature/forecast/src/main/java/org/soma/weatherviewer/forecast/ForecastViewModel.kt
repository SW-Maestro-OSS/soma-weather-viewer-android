package org.soma.weatherviewer.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.usecase.GetForecastUseCase
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
	private val getForecastUseCase: GetForecastUseCase
): ViewModel() {

	private val lat: Float = 37.5683f
	private val lon: Float = 126.9778f

	private val _forecastListFlow = MutableStateFlow<List<ForecastVO>>(emptyList())
	val forecastListFlow = _forecastListFlow.asStateFlow()
	fun fetchForecast(locale: Locale) {
		viewModelScope.launch {
			getForecastUseCase(
				lat = lat,
				lon = lon,
				locale = locale
			).collectLatest {
				_forecastListFlow.value = it
			}
		}
	}
}