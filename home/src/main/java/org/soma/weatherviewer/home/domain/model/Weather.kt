package org.soma.weatherviewer.home.domain.model

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String // http://openweathermap.org/img/wn/[icon]@2x.png
)