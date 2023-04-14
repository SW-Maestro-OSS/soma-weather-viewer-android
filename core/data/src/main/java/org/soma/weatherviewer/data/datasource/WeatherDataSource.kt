package org.soma.weatherviewer.data.datasource

import com.skydoves.sandwich.ApiResponse
import org.soma.weatherviewer.data.BuildConfig
import org.soma.weatherviewer.data.model.response.ForecastResponse
import org.soma.weatherviewer.data.model.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherDataSource {

	@GET("weather")
	suspend fun getCurrentWeather(
		@Query("lat") lat: Float,
		@Query("lon") lon: Float,
		@Query("appId") appId: String = BuildConfig.API_KEY
	) : ApiResponse<WeatherResponse>

	@GET("weather")
	suspend fun getCityWeather(
		@Query("q") cityName: String,
		@Query("appId") appId: String = BuildConfig.API_KEY
	) : ApiResponse<WeatherResponse>

	@GET("forecast")
	suspend fun getForecast(
		@Query("lat") lat: Float,
		@Query("lon") lon: Float,
		@Query("appId") appId: String = BuildConfig.API_KEY
	) : ApiResponse<ForecastResponse>
}