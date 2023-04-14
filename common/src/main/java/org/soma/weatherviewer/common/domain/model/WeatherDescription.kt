package org.soma.weatherviewer.common.domain.model

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.common.R

enum class WeatherDescription(val resId : Int?) {
    NULL(null),
    @SerializedName("Thunderstorm")
    CLEAR_SKY(R.string.thunderstorm),
    @SerializedName("Drizzle")
    FEW_CLOUDS(R.string.drizzle),
    @SerializedName("Rain")
    SCATTERED_CLOUDS(R.string.rain),
    @SerializedName("Snow")
    BROKEN_CLOUDS(R.string.snow),
    @SerializedName("Mist")
    SHOWER_RAIN(R.string.mist),
    @SerializedName("Smoke")
    RAIN(R.string.smoke),
    @SerializedName("Haze")
    THUNDERSTORM(R.string.haze),
    @SerializedName("Dust")
    SNOW(R.string.dust),
    @SerializedName("Fog")
    MIST(R.string.fog),
    @SerializedName("Sand")
    SAND(R.string.sand),
    @SerializedName("Dust")
    DUST(R.string.dust),
    @SerializedName("Ash")
    ASH(R.string.ash),
    @SerializedName("Squall")
    SQUALL(R.string.squall),
    @SerializedName("Tornado")
    TORNADO(R.string.tornado),
    @SerializedName("Clear")
    CLEAR(R.string.clear),
    @SerializedName("Clouds")
    CLOUDS(R.string.clouds)
}

