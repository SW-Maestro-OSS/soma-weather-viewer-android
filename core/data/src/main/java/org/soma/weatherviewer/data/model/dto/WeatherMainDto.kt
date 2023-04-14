package org.soma.weatherviewer.data.model.dto

import com.google.gson.annotations.SerializedName

data class WeatherMainDto(
	@SerializedName("temp")
	val currentTemp: Float,

	@SerializedName("temp_min")
	val minTemp: Float,

	@SerializedName("temp_max")
	val maxTemp: Float,

	@SerializedName("humidity")
	val humidity: Int
)
