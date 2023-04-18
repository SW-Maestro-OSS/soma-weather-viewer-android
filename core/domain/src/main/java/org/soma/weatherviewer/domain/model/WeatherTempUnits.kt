package org.soma.weatherviewer.domain.model

/**
 * Celsius - metric
 * Fahrenheit - imperial
 */
enum class WeatherTempUnits(val unit: String) {
	NONE("none"), CELSIUS("metric"), FAHRENHEIT("imperial");
}

fun WeatherTempUnits.translateToAPIUnit(): String = this.unit