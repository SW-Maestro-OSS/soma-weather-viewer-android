package org.soma.weatherviewer.common.model.dto

import org.soma.weatherviewer.common.model.entity.Weather

data class WeatherResponse(
    val list: List<Weather>
)