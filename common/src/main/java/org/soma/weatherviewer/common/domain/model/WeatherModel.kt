package org.soma.weatherviewer.common.domain.model

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.common.model.entity.Weather
import org.soma.weatherviewer.common.model.entity.WeatherInfo
import org.soma.weatherviewer.common.model.entity.WeatherMain

data class WeatherModel(
    val id: Long,
    val main: String,
    val description: String,
    val iconUrl: String,
    var temp: Float,
    var tempMax: Float,
    var tempMin: Float,
    val humidity: Int
) {
    fun applyCelsius() {
        this.temp = calcCelsius(this.temp)
        this.tempMax = calcCelsius(this.tempMax)
        this.tempMin = calcCelsius(this.tempMin)
    }

    fun applyFahrenheit() {
        this.temp = calcFahrenheit(this.temp)
        this.tempMax = calcFahrenheit(this.tempMax)
        this.tempMin = calcFahrenheit(this.tempMin)
    }

    private fun calcCelsius(temp: Float): Float = (temp - 32) / 1.8f

    private fun calcFahrenheit(temp: Float): Float = temp * 1.8f + 32

}