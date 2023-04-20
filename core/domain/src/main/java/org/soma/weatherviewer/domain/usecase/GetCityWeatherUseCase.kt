package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCityWeatherUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
) {
	operator fun invoke(
		cityName: String = "seoul",
		units: WeatherTempUnit = WeatherTempUnit.CELSIUS
	) = flow {
		weatherRepository.getCityWeather(cityName = cityName, units = units).collect {
			emit(it)
		}
	}
}