package org.soma.weatherviewer.domain.model

data class ForecastVO(
	val dateTime: String,

	val weatherId: Int,
	val weatherName: String,
	val description: String,
	val currentTemp: Float,
)