package org.soma.weatherviewer.common.domain.usecase

import kotlinx.coroutines.flow.first
import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.common.util.DataTranslator.toWeatherModelList
import org.soma.weatherviewer.common.util.TempType
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val dataStoreUseCase: DataStoreUseCase
) {
    suspend fun getFiveDaysWeather(lat: Float, lon: Float) : List<WeatherModel> {
        val weatherList = weatherRepository.getFiveDaysWeather(lat, lon).list
        val weatherModelList = weatherList.toWeatherModelList()
        return when (dataStoreUseCase.tempType.first()) {
            TempType.Celsius.name -> weatherModelList.map { it.applyKelvinToCelsius() }
            else -> weatherModelList.map { it.applyKelvinToFahrenheit() }
        }
    }
}