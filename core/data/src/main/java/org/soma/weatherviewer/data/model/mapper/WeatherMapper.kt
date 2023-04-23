package org.soma.weatherviewer.data.model.mapper

import org.soma.weatherviewer.data.model.response.WeatherResponse
import org.soma.weatherviewer.domain.model.WeatherName
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.model.mapper.DomainMapper
import org.soma.weatherviewer.domain.translator.TempTranslator
import org.soma.weatherviewer.domain.translator.UnixTimeTranslator
import java.util.*
import kotlin.math.roundToInt

object WeatherMapper : DomainMapper<WeatherVO, WeatherResponse> {

	override fun asDomain(data: WeatherResponse, unit: WeatherTempUnit, locale: Locale): WeatherVO {

		val currentTemp = calcDomainTemp(data.weatherMainInfo.currentTemp)
		val minTemp = calcDomainTemp(data.weatherMainInfo.minTemp)
		val maxTemp = calcDomainTemp(data.weatherMainInfo.maxTemp)

		return WeatherVO(
			dt = data.dt,
			dateTime = UnixTimeTranslator.getWeatherDateText(data.dt.toString(), locale),
			weatherId = data.weatherInfo[0].weatherId,
			weatherName = WeatherName.valueOf(data.weatherInfo[0].weatherName.uppercase()).getDescription(locale),
			description = data.weatherInfo[0].description,
			weatherIcon = "https://openweathermap.org/img/wn/${data.weatherInfo[0].icon}@2x.png",

			currentTemp = TempTranslator.translateTempWithUnit(currentTemp, unit),
			minTemp = TempTranslator.translateTempWithUnit(minTemp, unit),
			maxTemp = TempTranslator.translateTempWithUnit(maxTemp, unit),
			humidity = "${data.weatherMainInfo.humidity} %"
		)
	}

	private fun calcDomainTemp(temp: Float): Float = (temp*10).roundToInt() / 10.0f
}

fun WeatherResponse.asDomain(unit: WeatherTempUnit, locale: Locale = Locale.KOREA): WeatherVO {
	return WeatherMapper.asDomain(this, unit, locale)
}