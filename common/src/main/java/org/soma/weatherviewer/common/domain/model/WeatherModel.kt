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
    fun applyFahrenheitToCelsius() {
        this.temp = calcFahrenheitToCelsius(this.temp)
        this.tempMax = calcFahrenheitToCelsius(this.tempMax)
        this.tempMin = calcFahrenheitToCelsius(this.tempMin)
    }

    fun applyCelsiusToFahrenheit() {
        this.temp = calcCelsiusToFahrenheit(this.temp)
        this.tempMax = calcCelsiusToFahrenheit(this.tempMax)
        this.tempMin = calcCelsiusToFahrenheit(this.tempMin)
    }

    fun applyKelvinToCelsius() {
        this.temp = calcKelvinToCelsius(this.temp)
        this.tempMax = calcKelvinToCelsius(this.tempMax)
        this.tempMin = calcKelvinToCelsius(this.tempMin)
    }

    fun applyKelvinToFahrenheit() {
        this.temp = calcKelvinToFahrenheit(this.temp)
        this.tempMax = calcKelvinToFahrenheit(this.tempMax)
        this.tempMin = calcKelvinToFahrenheit(this.tempMin)
    }

    private fun calcFahrenheitToCelsius(temp: Float): Float = (temp - 32) / 1.8f

    private fun calcCelsiusToFahrenheit(temp: Float): Float = temp * 1.8f + 32

    private fun calcKelvinToCelsius(temp: Float): Float = temp - 273.15f

    private fun calcKelvinToFahrenheit(temp: Float): Float = (temp - 273.15f) * 9 /5f + 32

}