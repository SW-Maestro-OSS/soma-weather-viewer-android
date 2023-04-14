package org.soma.weatherviewer.domain.model

data class WeatherVO(
	val dateTime: String,

	val weatherId: Int,
	val weatherName: String,
	val description: String,

	val currentTemp: Float,
	val maxTemp: Float,
	val minTemp: Float,
	val humidity: Int
)