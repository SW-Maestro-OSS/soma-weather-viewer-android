package org.soma.weatherviewer.domain.model

data class ForecastVO(
	val dt: Long = 0L,
	val dateText: String = "",

	val weatherId: Int = 500,
	val weatherName: String = "",
	val description: String = "",
	val weatherIcon: String = "",
	val currentTemp: String = "",
)