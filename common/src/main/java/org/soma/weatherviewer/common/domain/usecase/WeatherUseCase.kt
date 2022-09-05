package org.soma.weatherviewer.common.domain.usecase

import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.model.entity.Weather
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.common.util.DataTranslator.toWeatherModelList
import org.soma.weatherviewer.common.util.WEATHER_KEY

class WeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getFiveDaysWeather(lat: Double, lon: Double) : List<WeatherModel> {
        val weatherList = weatherRepository.getFiveDaysWeather(lat, lon).list
        return weatherList.toWeatherModelList().map {
            it.apply { applyCelsius() }
        }
    }
}