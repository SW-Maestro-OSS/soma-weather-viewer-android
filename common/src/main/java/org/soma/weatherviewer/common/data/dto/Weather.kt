package org.soma.weatherviewer.common.data.dto

import org.soma.weatherviewer.common.data.WeatherDescription

data class Weather(
    val description: WeatherDescription?,
    val icon: String?,
    val id: Int?,
    val main: String?
)