package org.soma.weatherviewer.common.repository

import org.soma.weatherviewer.common.BuildConfig
import org.soma.weatherviewer.common.model.dto.WeatherResponse
import org.soma.weatherviewer.common.network.WeatherService
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getFiveDaysWeather(lat: Float, lon: Float): WeatherResponse {
        return weatherService.getFiveDaysWeather(lat, lon, BuildConfig.API_KEY)
    }

}