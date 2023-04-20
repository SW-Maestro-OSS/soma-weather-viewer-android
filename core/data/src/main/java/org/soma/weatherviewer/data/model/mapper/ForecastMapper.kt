package org.soma.weatherviewer.data.model.mapper

import org.soma.weatherviewer.data.model.response.ForecastResponse
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherName
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.mapper.DomainMapper
import org.soma.weatherviewer.domain.translator.TempTranslator
import org.soma.weatherviewer.domain.translator.UnixTimeTranslator
import java.util.*

object ForecastMapper : DomainMapper<List<ForecastVO>, ForecastResponse> {

	override fun asDomain(data: ForecastResponse, unit: WeatherTempUnit, locale: Locale): List<ForecastVO> {
		return data.weatherList.map { weather ->
			ForecastVO(
				dt = weather.dt,
				dateText = UnixTimeTranslator.getForecastDateText(weather.dt.toString(), locale),
				weatherId = weather.weatherInfo[0].weatherId,
				weatherName = WeatherName.valueOf(weather.weatherInfo[0].weatherName.uppercase()).getDescription(locale),
				description = weather.weatherInfo[0].description,
				weatherIcon = "https://openweathermap.org/img/wn/${weather.weatherInfo[0].icon}@2x.png",
				currentTemp = TempTranslator.translateTempWithUnit(weather.weatherMainInfo.currentTemp, unit)
			)
		}
	}
}

fun ForecastResponse.asDomain(unit: WeatherTempUnit, locale: Locale = Locale.KOREA): List<ForecastVO> {
	return ForecastMapper.asDomain(this, unit, locale)
}