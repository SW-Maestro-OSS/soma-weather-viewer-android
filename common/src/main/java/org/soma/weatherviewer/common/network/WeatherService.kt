package org.soma.weatherviewer.common.network

import org.soma.weatherviewer.common.model.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast")
    suspend fun getFiveDaysWeather(@Query("lat") lat: Float, @Query("lon") lon: Float, @Query("appId") appId: String) : WeatherResponse
}