package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(
		lat: Float,
		lon: Float,
		units: WeatherTempUnits = WeatherTempUnits.Celsius
	) = flow {
		weatherRepository.getForecast(lat = lat, lon = lon, units = units).collect {
			emit(it)
		}
	}
}