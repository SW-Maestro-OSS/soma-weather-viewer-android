package org.soma.weatherviewer.common.util

import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.model.entity.Weather

object DataTranslator {
    fun List<Weather>.toWeatherModelList(): List<WeatherModel> {
        val weatherList = mutableListOf<WeatherModel>()
        this.forEach { data ->
            weatherList.add(
                WeatherModel(
                    data.weather[0].id,
                    data.weather[0].main,
                    data.weather[0].description,
                    data.dt_txt,
                    "http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png",
                    data.main.temp,
                    data.main.tempMax,
                    data.main.tempMin,
                    data.main.humidity
                )
            )
        }
        return weatherList.toList()
    }



    }