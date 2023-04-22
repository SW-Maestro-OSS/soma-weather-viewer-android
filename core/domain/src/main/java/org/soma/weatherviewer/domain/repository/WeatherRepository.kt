package org.soma.weatherviewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.WeatherVO
import java.util.*

interface WeatherRepository {

	fun getCurrentWeather(lat: Float, lon: Float, units: WeatherTempUnit, locale: Locale): Flow<WeatherVO>

	fun getCityWeather(cityName: String, units: WeatherTempUnit, locale: Locale, onError: (String?) -> Unit): Flow<WeatherVO>

	fun getForecast(lat: Float, lon: Float, units: WeatherTempUnit, locale: Locale): Flow<List<ForecastVO>>
}