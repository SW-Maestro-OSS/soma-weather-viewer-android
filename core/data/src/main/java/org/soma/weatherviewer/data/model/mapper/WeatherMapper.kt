package org.soma.weatherviewer.data.model.mapper

import org.soma.weatherviewer.data.model.response.WeatherResponse
import org.soma.weatherviewer.domain.model.WeatherVO

object WeatherMapper : DomainMapper<WeatherVO, WeatherResponse> {

	override fun asDomain(data: WeatherResponse): WeatherVO {
		return WeatherVO(
			dateTime = data.dateTime,

			weatherId = data.weatherInfo[0].weatherId,
			weatherName = data.weatherInfo[0].weatherName,
			description = data.weatherInfo[0].description,

			currentTemp = data.weatherMainInfo.currentTemp,
			minTemp = data.weatherMainInfo.minTemp,
			maxTemp = data.weatherMainInfo.maxTemp,
			humidity = data.weatherMainInfo.humidity
		)
	}
}

fun WeatherResponse.asDomain(): WeatherVO {
	return WeatherMapper.asDomain(this)
}