package org.soma.weatherviewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.WeatherVO
import java.util.*

interface WeatherRepository {

	/**
	 * 위도, 경도에 따라 오늘의 날씨(Weather) 정보를 얻는 기능
	 */
	fun getCurrentWeather(lat: Float, lon: Float, units: WeatherTempUnit, locale: Locale): Flow<WeatherVO>

	/**
	 * 해당 도시의 날씨 (Weather) 정보를 얻는 기능
	 */
	fun getCityWeather(cityName: String, units: WeatherTempUnit, locale: Locale, onError: (String?) -> Unit): Flow<WeatherVO>

	/**
	 * 위도, 경도에 따라 5일치 정보(Forecast)를 얻는 기능
	 */
	fun getForecast(lat: Float, lon: Float, units: WeatherTempUnit, locale: Locale): Flow<List<ForecastVO>>
}