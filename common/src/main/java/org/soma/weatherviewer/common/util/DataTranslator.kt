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
                    getDateYear(data.dt_txt),
                    getDateMonth(data.dt_txt),
                    getDateDay(data.dt_txt),
                    data.weather[0].description,
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

    private fun getDateYear(datetime: String) = datetime.substring(0,4)
    private fun getDateMonth(datetime: String) = datetime.substring(6, 7)
    private fun getDateDay(datetime: String) = datetime.substring(8, 10)
}