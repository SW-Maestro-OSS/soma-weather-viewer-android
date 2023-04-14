package org.soma.weatherviewer.data.model.mapper

import org.soma.weatherviewer.data.model.response.ForecastResponse
import org.soma.weatherviewer.domain.model.ForecastVO

object ForecastMapper : DomainMapper<List<ForecastVO>, ForecastResponse> {

	override fun asDomain(data: ForecastResponse): List<ForecastVO> {
		return data.weatherList.map { weather ->
			ForecastVO(
				dateTime = weather.dateTime,
				weatherId = weather.weatherInfo[0].weatherId,
				weatherName = weather.weatherInfo[0].weatherName,
				description = weather.weatherInfo[0].description,
				currentTemp = weather.weatherMainInfo.currentTemp
			)
		}
	}
}

fun ForecastResponse.asDomain(): List<ForecastVO> {
	return ForecastMapper.asDomain(this)
}