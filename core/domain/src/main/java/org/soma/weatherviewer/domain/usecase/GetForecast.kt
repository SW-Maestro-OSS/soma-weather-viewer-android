package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecast @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(
		lat: Float,
		lon: Float
	) = flow {
		emit(weatherRepository.getForecast(lat = lat, lon = lon))
	}
}