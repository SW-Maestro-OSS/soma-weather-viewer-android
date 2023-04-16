package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetHomeDataUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(
		lat: Float,
		lon: Float,
		units: WeatherTempUnits = WeatherTempUnits.Celsius
	): Flow<HomeModel> {
		return combine(
			weatherRepository.getCurrentWeather(
				lat = lat,
				lon = lon,
				units = units
			),
			weatherRepository.getForecast(
				lat = lat,
				lon = lon,
				units = units
			),
		) { weather, forecast ->
			HomeModel(
				weather,
				forecast
			)
		}
	}
}