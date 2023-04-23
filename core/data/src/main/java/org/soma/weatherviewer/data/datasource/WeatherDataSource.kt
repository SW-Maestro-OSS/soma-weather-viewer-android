package org.soma.weatherviewer.data.datasource

import com.skydoves.sandwich.ApiResponse
import org.soma.weatherviewer.data.BuildConfig
import org.soma.weatherviewer.data.model.response.ForecastResponse
import org.soma.weatherviewer.data.model.response.WeatherResponse
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.translateToAPIUnit
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataSource {

	/**
	 * 위도, 경도에 따라 오늘의 날씨(Weather) 정보를 얻는 API
	 */
	@GET("weather")
	suspend fun getCurrentWeather(
		@Query("lat") lat: Float,
		@Query("lon") lon: Float,
		@Query("appId") appId: String = BuildConfig.API_KEY,
		@Query("units") units: String = WeatherTempUnit.CELSIUS.translateToAPIUnit(),
		@Query("lang") lang: String = "en"
	) : ApiResponse<WeatherResponse>

	/**
	 * 해당 도시의 날씨 (Weather) 정보를 얻는 API
	 */
	@GET("weather")
	suspend fun getCityWeather(
		@Query("q") cityName: String,
		@Query("appId") appId: String = BuildConfig.API_KEY,
		@Query("units") units: String = WeatherTempUnit.CELSIUS.translateToAPIUnit(),
		@Query("lang") lang: String = "en"
	) : ApiResponse<WeatherResponse>

	/**
	 * 위도, 경도에 따라 5일치 정보(Forecast)를 얻는 API
	 */
	@GET("forecast")
	suspend fun getForecast(
		@Query("lat") lat: Float,
		@Query("lon") lon: Float,
		@Query("appId") appId: String = BuildConfig.API_KEY,
		@Query("units") units: String = WeatherTempUnit.CELSIUS.translateToAPIUnit(),
		@Query("lang") lang: String = "en"
	) : ApiResponse<ForecastResponse>
}