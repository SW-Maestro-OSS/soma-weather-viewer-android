package org.soma.weatherviewer.common.repository

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.common.model.dto.WeatherResponse
import org.soma.weatherviewer.common.util.WEATHER_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepository {
    suspend fun getFiveDaysWeather(lat: Float, lon: Float) : WeatherResponse
}