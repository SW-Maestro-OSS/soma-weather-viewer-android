package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeather @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(
		cityName: String
	) = flow {
		emit(weatherRepository.getCityWeather(cityName = cityName))
	}
}