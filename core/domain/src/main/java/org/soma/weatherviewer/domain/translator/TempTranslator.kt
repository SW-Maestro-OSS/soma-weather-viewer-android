package org.soma.weatherviewer.domain.translator

import org.soma.weatherviewer.domain.model.WeatherTempUnit

object TempTranslator {

	fun translateTempWithUnit(temp: Float, unit: WeatherTempUnit): String {
		return when(unit) {
			WeatherTempUnit.CELSIUS -> "${temp}°C"
			WeatherTempUnit.FAHRENHEIT -> "${temp}°F"
			else -> ""
		}
	}
}