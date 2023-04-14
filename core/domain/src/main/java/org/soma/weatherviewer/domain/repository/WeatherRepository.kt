package org.soma.weatherviewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherVO

interface WeatherRepository {

	fun getCurrentWeather(lat: Float, lon: Float): Flow<WeatherVO>

	fun getCityWeather(cityName: String): Flow<WeatherVO>

	fun getForecast(lat: Float, lon: Float): Flow<List<ForecastVO>>
}