package org.soma.weatherviewer.data.model.dto

import com.google.gson.annotations.SerializedName

data class WeatherInfoDto(
	@SerializedName("id")
	val weatherId: Int,

	@SerializedName("main")
	val weatherName: String,

	@SerializedName("description")
	val description: String
)