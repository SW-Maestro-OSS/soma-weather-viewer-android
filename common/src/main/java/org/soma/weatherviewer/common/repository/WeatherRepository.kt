package org.soma.weatherviewer.common.repository

import org.soma.weatherviewer.common.model.dto.WeatherResponse
import org.soma.weatherviewer.common.util.WEATHER_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepository {
    @GET("forecast")
    suspend fun getFiveDaysWeather(@Query("lat") lat: Double, @Query("lon") lon: Double, @Query("appId") appId: String = WEATHER_KEY) : WeatherResponse
}