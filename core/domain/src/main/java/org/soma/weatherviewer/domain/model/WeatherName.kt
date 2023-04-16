package org.soma.weatherviewer.domain.model

import java.util.*

enum class WeatherName(
	private val korean: String,
) {
	CLEAR("맑음"),
	CLEAR_SKY("맑은 하늘"),
	FEW_CLOUDS("조금 흐림"),
	CLOUDS("흐림"),
	SCATTERED_CLOUDS("흐림"),
	BROKEN_CLOUDS("매우 흐림"),
	SHOWER_RAIN("소나기"),
	RAIN("비"),
	THOUDERSTOM("천둥번개"),
	SNOW("눈"),
	DRIZZLE("이슬비"),
	SMOKE("연기"),
	HAZE("아지랑이"),
	MIST("짙은 안개"),
	FOG("안개"),
	SAND("모래"),
	DUST("먼지"),
	ASH("재"),
	SQUALL("돌풍"),
	TORNADO("토네이도");

	fun getDescription(locale: Locale): String {
		return when (locale) {
			Locale.KOREA -> this.korean
			else -> this.name.lowercase()
		}
	}
}