package org.soma.weatherviewer.home.domain.model

data class WeatherModel(
    val weather: Weather,
    val temp: Float,
    val tempMax: Float,
    val tempMin: Float,
    val humidity: Int
)