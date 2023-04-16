package org.soma.weatherviewer.domain.model

/**
 * Celsius - metric
 * Fahrenheit - imperial
 */
enum class WeatherTempUnits(val unit: String) {
	Celsius("metric"), Fahrenheit("imperial");
}

fun WeatherTempUnits.translateToAPIUnit(): String = this.unit