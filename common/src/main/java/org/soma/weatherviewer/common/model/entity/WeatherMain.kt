package org.soma.weatherviewer.common.model.entity

data class WeatherMain(
    var temp: Float,
    var temp_min: Float,
    var temp_max: Float,
    val humidity: Int
)