package org.soma.weatherviewer.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.Latitude
import org.soma.weatherviewer.domain.model.Longitude
import org.soma.weatherviewer.domain.usecase.GetForecastUseCase
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
	private val getForecastUseCase: GetForecastUseCase
): ViewModel() {

	private val _forecastListFlow = MutableStateFlow<List<ForecastVO>>(emptyList())
	val forecastListFlow = _forecastListFlow.asStateFlow()

	fun fetchForecast() {
		viewModelScope.launch {
			getForecastUseCase(defaultCoordinates).collectLatest {
				_forecastListFlow.value = it
			}
		}
	}

	companion object {
		private val defaultCoordinates = Coordinates(
			latitude = Latitude(37.5683f),
			longitude = Longitude(126.9778f)
		)
	}
}