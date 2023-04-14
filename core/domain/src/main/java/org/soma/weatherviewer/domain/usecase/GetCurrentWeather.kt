package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeather @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(
		lat: Float,
		lon: Float
	) = flow {
		emit(weatherRepository.getCurrentWeather(lat = lat, lon = lon))
	}
}