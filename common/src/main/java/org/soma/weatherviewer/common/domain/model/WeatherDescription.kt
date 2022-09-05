package org.soma.weatherviewer.common.domain.model

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.common.R

enum class WeatherDescription(val resId : Int) {
    @SerializedName("clear sky")
    CLEAR_SKY(R.string.clear_sky),
    @SerializedName("few clouds")
    FEW_CLOUDS(R.string.few_clouds),
    @SerializedName("scattered clouds")
    SCATTERED_CLOUDS(R.string.scattered_clouds),
    @SerializedName("broken clouds")
    BROKEN_CLOUDS(R.string.broken_clouds),
    @SerializedName("shower rain")
    SHOWER_RAIN(R.string.shower_rain),
    @SerializedName("rain")
    RAIN(R.string.rain),
    @SerializedName("thunderstorm")
    THUNDERSTORM(R.string.thunderstorm),
    @SerializedName("snow")
    SNOW(R.string.snow),
    @SerializedName("mist")
    MIST(R.string.mist)
}

