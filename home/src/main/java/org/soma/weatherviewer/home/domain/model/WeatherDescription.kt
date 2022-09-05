package org.soma.weatherviewer.home.domain.model

enum class WeatherDescription(description: String) {
    CLEAR_SKY("clear sky"),
    FEW_CLOUDS("few clouds"),
    SCATTERED_CLOUDS("scattered clouds"),
    BROKEN_CLOUDS("broken clouds"),
    SHOWER_RAIN("shower rain"),
    RAIN("rain"),
    THUNDERSTORM("thunderstorm"),
    SNOW("snow"),
    MIST("mist")
}