package org.soma.weatherviewer.common.util

import org.soma.weatherviewer.common.model.entity.Weather
import org.soma.weatherviewer.common.util.DataTranslator.processIcon

object DataTranslator {
    fun List<Weather>.processIcon() : List<Weather> {
        val weatherList = mutableListOf<Weather>()
        this.forEach { data ->
            data.weather.map {
                it.icon = "http://openweathermap.org/img/wn/${it.icon}@2x.png"
            }
            weatherList.add(data)
        }
        return weatherList
    }

    fun List<Weather>.applyCelsius() : List<Weather> {
        val weatherList = mutableListOf<Weather>()
        this.forEach { data ->
            data.main.temp = calcCelsius(data.main.temp)
            data.main.temp_max = calcCelsius(data.main.temp_max)
            data.main.temp_min = calcCelsius(data.main.temp_min)
            weatherList.add(data)
        }
        return weatherList
    }

    fun List<Weather>.applyFahrenheit() : List<Weather> {
        val weatherList = mutableListOf<Weather>()
        this.forEach { data ->
            data.weather.map {
                data.main.temp = calcFahrenheit(data.main.temp)
                data.main.temp_max = calcFahrenheit(data.main.temp_max)
                data.main.temp_min = calcFahrenheit(data.main.temp_min)
                weatherList.add(data)
            }
            weatherList.add(data)
        }
        return weatherList
    }

    private fun calcCelsius(temp: Float) : Float
        = (temp - 32) / 1.8f

    private fun calcFahrenheit(temp: Float) : Float
        = temp * 1.8f + 32
}