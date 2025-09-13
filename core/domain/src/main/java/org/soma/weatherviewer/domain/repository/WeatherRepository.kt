package org.soma.weatherviewer.domain.repository

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.WeatherVO

interface WeatherRepository {

	/**
	 * 위도, 경도에 따라 오늘의 날씨(Weather) 정보를 얻는 기능
	 */
	fun getCurrentWeather(coordinates: Coordinates): Flow<WeatherVO>

	/**
	 * 해당 도시의 날씨 (Weather) 정보를 얻는 기능
	 */
	fun getCityWeather(cityName: String, onError: (String?) -> Unit): Flow<WeatherVO>

	/**
	 * 위도, 경도에 따라 5일치 정보(Forecast)를 얻는 기능
	 */
	fun getForecast(coordinates: Coordinates): Flow<List<ForecastVO>>

	fun getSearchCityName(): Flow<String>

	fun getUserTempUnit(): Flow<WeatherTempUnit>

	fun storeUserTempUnit(unit : WeatherTempUnit): Flow<Boolean>

	fun storeSearchCityName(cityName: String): Flow<Boolean>
}