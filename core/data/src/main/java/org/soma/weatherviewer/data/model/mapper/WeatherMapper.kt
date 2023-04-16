package org.soma.weatherviewer.data.model.mapper

import org.soma.weatherviewer.domain.translator.UnixTimeTranslator
import org.soma.weatherviewer.domain.model.mapper.DomainMapper
import org.soma.weatherviewer.data.model.response.WeatherResponse
import org.soma.weatherviewer.domain.model.WeatherVO
import java.util.*
import kotlin.math.roundToInt

object WeatherMapper : DomainMapper<WeatherVO, WeatherResponse> {

	override fun asDomain(data: WeatherResponse, locale: Locale): WeatherVO {

		return WeatherVO(
			dt = data.dt,
			dateTime = UnixTimeTranslator.getWeatherDateText(data.dt.toString(), locale),
			weatherId = data.weatherInfo[0].weatherId,
			weatherName = data.weatherInfo[0].weatherName,
			description = data.weatherInfo[0].description,
			weatherIcon = "https://openweathermap.org/img/wn/${data.weatherInfo[0].icon}@2x.png",

			currentTemp = "${(data.weatherMainInfo.currentTemp * 10).roundToInt() / 10.0f}℃",
			minTemp = "${(data.weatherMainInfo.minTemp * 10).roundToInt() / 10.0f}℃",
			maxTemp = "${(data.weatherMainInfo.maxTemp * 10).roundToInt() / 10.0f}℃",
			humidity = "${data.weatherMainInfo.humidity}%"
		)
	}
}

fun WeatherResponse.asDomain(locale: Locale = Locale.KOREA): WeatherVO {
	return WeatherMapper.asDomain(this, locale)
}