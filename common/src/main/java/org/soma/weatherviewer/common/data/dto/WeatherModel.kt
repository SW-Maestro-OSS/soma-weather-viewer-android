package org.soma.weatherviewer.common.data.dto

data class WeatherModel(
    val weather: List<Weather>?,
    val main: Main?,
)