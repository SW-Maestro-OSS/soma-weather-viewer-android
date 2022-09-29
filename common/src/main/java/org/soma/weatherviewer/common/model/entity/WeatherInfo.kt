package org.soma.weatherviewer.common.model.entity

import org.soma.weatherviewer.common.domain.model.WeatherDescription

data class WeatherInfo(
    val id: Long,
    val main: WeatherDescription,
    val description: String,
    var icon: String
)
