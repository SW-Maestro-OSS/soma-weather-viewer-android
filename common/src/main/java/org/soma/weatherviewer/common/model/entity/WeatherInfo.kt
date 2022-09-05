package org.soma.weatherviewer.common.model.entity

data class WeatherInfo(
    val id: Long,
    val main: String,
    val description: String,
    var icon: String
)
