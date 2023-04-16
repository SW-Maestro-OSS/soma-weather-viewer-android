package org.soma.weatherviewer.domain.model

data class HomeModel(
	val weather: WeatherVO,
	val forecast: List<ForecastVO>,
)