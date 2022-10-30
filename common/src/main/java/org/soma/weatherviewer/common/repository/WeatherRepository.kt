package org.soma.weatherviewer.common.repository

import org.soma.weatherviewer.common.model.dto.WeatherResponse

interface WeatherRepository {
    suspend fun getFiveDaysWeather(lat: Float, lon: Float) : WeatherResponse
}