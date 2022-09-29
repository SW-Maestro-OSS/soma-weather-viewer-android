package org.soma.weatherviewer.common.model.entity

import com.google.gson.annotations.SerializedName

data class WeatherMain(
    var temp: Float,
    @SerializedName("temp_min")
    var tempMin: Float,
    @SerializedName("temp_max")
    var tempMax: Float,
    val humidity: Int
)