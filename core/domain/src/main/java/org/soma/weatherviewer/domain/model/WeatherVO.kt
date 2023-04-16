package org.soma.weatherviewer.domain.model

data class WeatherVO(
	val dt: Long = 0L,
	val dateTime: String = "",

	val weatherId: Int = 500,
	val weatherName: String = "",
	val description: String = "",
	val weatherIcon: String = "",

	val currentTemp: String = "",
	val maxTemp: String = "",
	val minTemp: String = "",
	val humidity: String = ""
)