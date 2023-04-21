package org.soma.weatherviewer.domain.model

/**
 * Celsius - metric
 * Fahrenheit - imperial
 */
enum class WeatherTempUnit(val unit: String) {
	NONE("none"), CELSIUS("metric"), FAHRENHEIT("imperial");
}

fun WeatherTempUnit.translateToAPIUnit(): String = this.unit