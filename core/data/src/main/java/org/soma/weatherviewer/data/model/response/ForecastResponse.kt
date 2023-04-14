package org.soma.weatherviewer.data.model.response

import com.google.gson.annotations.SerializedName

data class ForecastResponse(
	@SerializedName("list")
	val weatherList: List<WeatherResponse>
)