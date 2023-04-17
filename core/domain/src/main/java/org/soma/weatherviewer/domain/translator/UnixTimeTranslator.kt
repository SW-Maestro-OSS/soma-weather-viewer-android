package org.soma.weatherviewer.domain.translator

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.*

object UnixTimeTranslator {
	/**
	 * Unix Time을 DateFormat으로 변환
	 */
	private fun translateUnixToDate(timestampStr: String, locale: Locale = Locale.KOREA): String {
		val timestamp: Long = timestampStr.toLong()
		val date = Date(timestamp * 1000L)
		val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale)
		sdf.timeZone = TimeZone.getTimeZone("GMT+9")
		return sdf.format(date)
	}

	/**
	 * Weather 화면에서 제공할 DateText 생성 (locale에 따른 문자열 반환)
	 */
	fun getWeatherDateText(timestampStr: String, locale: Locale = Locale.KOREA): String {
		val dateInfo = translateUnixToDate(timestampStr).split(" ")
		val dateData = dateInfo[0].split("-")
		val timeData = dateInfo[1].split(":")
		val dayOfWeek = LocalDate.of(dateData[0].toInt(), dateData[1].toInt(), dateData[2].toInt()).dayOfWeek

		return when (locale) {
			Locale.KOREA -> "${dateData[1]}월 ${dateData[2]}일 (${dayOfWeek.getDisplayName(TextStyle.SHORT, locale)}) ${timeData[0]}시"
			else -> ""
		}
	}

	/**
	 * Forecast 화면에서 제공할 DateText 생성 (locale에 따른 문자열 반환)
	 */
	fun getForecastDateText(timestampStr: String, locale: Locale = Locale.KOREA): String {
		val dateInfo = translateUnixToDate(timestampStr).split(" ")
		val dateData = dateInfo[0].split("-")
		val timeData = dateInfo[1].split(":")
		val dayOfWeek = LocalDate.of(dateData[0].toInt(), dateData[1].toInt(), dateData[2].toInt()).dayOfWeek

		return when (locale) {
			Locale.KOREA -> "${timeData[0]}:${timeData[1]} (${dayOfWeek.getDisplayName(TextStyle.SHORT, locale)})"
			else -> ""
		}
	}
}