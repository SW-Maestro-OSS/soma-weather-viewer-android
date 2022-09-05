package org.soma.weatherviewer.home.data.repository

import org.soma.weatherviewer.home.domain.model.Weather
import org.soma.weatherviewer.home.domain.model.WeatherModel

// TODO: WeatherService injection
class WeatherListRepository {
    suspend fun readWeatherList(lat: Float, lon: Float): List<WeatherModel> {
        // TODO: Retrofit2로 데이터 전달 받고 Translator로 Model 변환
        return listOf(
            WeatherModel(
                Weather(0, "Rain", "rain", "http://openweathermap.org/img/wn/10d@2x.png"),
                10.1f,10.2f,10.3f, 69
            ),
            WeatherModel(
                Weather(0, "Rain", "rain", "http://openweathermap.org/img/wn/10d@2x.png"),
                10.1f,10.2f,10.3f, 69
            )
        )
    }
}