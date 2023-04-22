package org.soma.weatherviewer.domain.translator

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalTime
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

		// 년도, 월, 일 과 관련된 정보
		val dateData = dateInfo[0].split("-")
		val localeDate = LocalDate.of(dateData[0].toInt(), dateData[1].toInt(), dateData[2].toInt())
		val localMonth = localeDate.month.toString()
		val month = localMonth.substring(0, 1) + localMonth.substring(1, 3).lowercase()
		val dayOfWeek = LocalDate.of(dateData[0].toInt(), dateData[1].toInt(), dateData[2].toInt())
			.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)

		// 시간과 관련된 정보
		val timeData = dateInfo[1].split(":")
		val localTime = LocalTime.of(timeData[0].toInt(), timeData[1].toInt())
		val hour = (localTime.hour%12).toString().padStart(2, '0')
		val ampm = if(localTime.hour / 12 == 0) "AM" else "PM"
		val minute = localTime.minute.toString().padStart(2, '0')

		return when (locale) {
			Locale.KOREA -> "${dateData[1]}월 ${dateData[2]}일 (${dayOfWeek}) ${timeData[0]}시"
			Locale.UK -> "${dayOfWeek}, 22 $month, ${hour}:${minute} $ampm"
			else -> "${dayOfWeek}, $month 22, ${hour}:${minute} $ampm"
		}
	}

	/**
	 * Forecast 화면에서 제공할 DateText 생성 (locale에 따른 문자열 반환)
	 */
	fun getForecastDateText(timestampStr: String, locale: Locale = Locale.KOREA): String {
		val dateInfo = translateUnixToDate(timestampStr).split(" ")

		// 년도, 월, 일 과 관련된 정보
		val dateData = dateInfo[0].split("-")
		val localeDate = LocalDate.of(dateData[0].toInt(), dateData[1].toInt(), dateData[2].toInt())
		val dayOfWeek = localeDate.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)

		// 시간과 관련된 정보
		val timeData = dateInfo[1].split(":")
		val localTime = LocalTime.of(timeData[0].toInt(), timeData[1].toInt())
		val hour = (localTime.hour%12).toString().padStart(2, '0')
		val ampm = if(localTime.hour / 12 == 0) "AM" else "PM"
		val minute = localTime.minute.toString().padStart(2, '0')

		return when (locale) {
			Locale.KOREA -> "${timeData[0]}:${timeData[1]} (${dayOfWeek})"
			else -> "${dayOfWeek}, ${hour}:${minute} $ampm"
		}
	}
}