package org.soma.weatherviewer.forecast

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.usecase.GetForecastUseCase
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
	getForecastUseCase: GetForecastUseCase
): ViewModel() {

	private val lat: Float = 37.5683f
	private val lon: Float = 126.9778f

	val forecastListFlow = getForecastUseCase(
		lat = lat,
		lon = lon,
		units = WeatherTempUnits.CELSIUS
	).stateIn(
		scope = viewModelScope,
		started = SharingStarted.Eagerly,
		initialValue = emptyList()
	)
}