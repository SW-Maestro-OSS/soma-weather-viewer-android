package org.soma.weatherviewer.common.model.entity

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.common.model.entity.WeatherInfo
import org.soma.weatherviewer.common.model.entity.WeatherMain

data class Weather (
    val weather: List<WeatherInfo>,
    val main: WeatherMain,
    val dt_txt: String
)