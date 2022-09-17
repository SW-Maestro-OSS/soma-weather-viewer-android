package org.soma.weatherviewer.common.domain.model

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.common.model.entity.Weather
import org.soma.weatherviewer.common.model.entity.WeatherInfo
import org.soma.weatherviewer.common.model.entity.WeatherMain

data class WeatherModel(
    val id: Long,
    val main: String,
    val dt_txt: String,
    val description: String,
    val iconUrl: String,
    var temp: Float,
    var tempMax: Float,
    var tempMin: Float,
    val humidity: Int
) {
    fun applyFahrenheitToCelsius(): WeatherModel {
        return this.apply {
            temp = calcFahrenheitToCelsius(this.temp)
            tempMax = calcFahrenheitToCelsius(this.tempMax)
            tempMin = calcFahrenheitToCelsius(this.tempMin)
        }
    }

    fun applyCelsiusToFahrenheit(): WeatherModel {
        return this.apply {
            temp = calcCelsiusToFahrenheit(this.temp)
            tempMax = calcCelsiusToFahrenheit(this.tempMax)
            tempMin = calcCelsiusToFahrenheit(this.tempMin)
        }
    }

    fun applyKelvinToCelsius(): WeatherModel {
        return this.apply {
            temp = calcKelvinToCelsius(this.temp)
            tempMax = calcKelvinToCelsius(this.tempMax)
            tempMin = calcKelvinToCelsius(this.tempMin)
        }
    }

    fun applyKelvinToFahrenheit(): WeatherModel {
        return this.apply {
            temp = calcKelvinToFahrenheit(this.temp)
            tempMax = calcKelvinToFahrenheit(this.tempMax)
            tempMin = calcKelvinToFahrenheit(this.tempMin)
        }
    }

    private fun calcFahrenheitToCelsius(temp: Float): Float = (temp - 32) / 1.8f

    private fun calcCelsiusToFahrenheit(temp: Float): Float = temp * 1.8f + 32

    private fun calcKelvinToCelsius(temp: Float): Float = temp - 273.15f

    private fun calcKelvinToFahrenheit(temp: Float): Float = (temp - 273.15f) * 9 /5f + 32

}