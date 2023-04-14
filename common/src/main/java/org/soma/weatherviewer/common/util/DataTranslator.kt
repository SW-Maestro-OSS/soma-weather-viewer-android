package org.soma.weatherviewer.common.util

import org.soma.weatherviewer.common.domain.model.WeatherModel
import org.soma.weatherviewer.common.model.entity.Weather

object DataTranslator {
    fun List<Weather>.toWeatherModelList(): List<WeatherModel> {
        val weatherList = mutableListOf<WeatherModel>()
        this.forEach { data ->

            val dateData = data.dt_txt.split("-")
            val day = dateData[2].split(" ")
            val time = day[1].split(":")

            if (time[0] == "12") {
                weatherList.add(
                    WeatherModel(
                        data.weather[0].id,
                        data.weather[0].main,
                        dateData[0],
                        dateData[1],
                        day[0],
                        data.weather[0].description,
                        "http://openweathermap.org/img/wn/${data.weather[0].icon}@2x.png",
                        data.main.temp,
                        data.main.tempMax,
                        data.main.tempMin,
                        data.main.humidity
                    )
                )
            }
        }
        return weatherList.toList()
    }
}