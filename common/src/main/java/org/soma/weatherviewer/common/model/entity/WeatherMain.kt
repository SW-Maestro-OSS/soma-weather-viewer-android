package org.soma.weatherviewer.common.model.entity

data class WeatherMain(
    var temp: Float,
    var tempMin: Float,
    var tempMax: Float,
    val humidity: Int
)