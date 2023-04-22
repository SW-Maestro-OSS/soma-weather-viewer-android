package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherViewerDataStore: WeatherViewerDataStore
){
	operator fun invoke(
		lat: Float,
		lon: Float,
		locale: Locale
	) = flow {
		weatherViewerDataStore.getUserTempUnit().collect { unit ->
			weatherRepository.getForecast(
				lat = lat,
				lon = lon,
				units = unit,
				locale = locale
			).collect {
				emit(it)
			}
		}
	}
}