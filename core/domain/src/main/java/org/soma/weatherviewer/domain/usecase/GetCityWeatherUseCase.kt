package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherViewerDataStore: WeatherViewerDataStore
) {
	operator fun invoke(
		cityName: String = "seoul",
		locale: Locale,
		onError: (String?) -> Unit
	) = flow {
		weatherViewerDataStore.getUserTempUnit().collect { unit ->
			weatherRepository.getCityWeather(
				cityName = cityName,
				units = unit,
				locale = locale,
				onError = onError
			).collect {
				emit(it)
			}
		}
	}
}