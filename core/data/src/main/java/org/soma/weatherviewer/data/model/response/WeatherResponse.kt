package org.soma.weatherviewer.data.model.response

import com.google.gson.annotations.SerializedName
import org.soma.weatherviewer.data.model.dto.WeatherInfoDto
import org.soma.weatherviewer.data.model.dto.WeatherMainDto

data class WeatherResponse(
	@SerializedName("dt")
	val dateTime: String,

	@SerializedName("weather")
	val weatherInfo: List<WeatherInfoDto>,

	@SerializedName("main")
	val weatherMainInfo: WeatherMainDto
)
