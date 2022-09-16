package org.soma.weatherviewer.common.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.common.model.dto.WeatherResponse
import org.soma.weatherviewer.common.network.WeatherService
import org.soma.weatherviewer.common.util.WEATHER_KEY
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherService: WeatherService
) : WeatherRepository {

    override suspend fun getFiveDaysWeather(lat: Float, lon: Float): WeatherResponse {
        return weatherService.getFiveDaysWeather(lat, lon, WEATHER_KEY)
    }

}