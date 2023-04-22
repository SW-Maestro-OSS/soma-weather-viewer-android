package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherViewerDataStore: WeatherViewerDataStore
) {
	suspend operator fun invoke(
		lat: Float,
		lon: Float,
		locale: Locale
	): Flow<HomeModel> = flow {

		weatherViewerDataStore.getUserTempUnit().collect { unit ->
			combine(
				weatherRepository.getCurrentWeather(
					lat = lat,
					lon = lon,
					units = unit,
					locale = locale
				),
				weatherRepository.getForecast(
					lat = lat,
					lon = lon,
					units = unit,
					locale = locale
				),
			) { weather, forecast ->
				HomeModel(weather, forecast)
			}.collect {
				emit(it)
			}
		}
	}
}