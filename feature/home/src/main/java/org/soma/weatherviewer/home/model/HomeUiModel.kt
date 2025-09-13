package org.soma.weatherviewer.home.model

import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.model.WeatherVO

/**
 * @author   JGeun
 * @created  2025/09/13 
 */
data class HomeUiModel(
	val weather: WeatherVO,
	val forecast: List<ForecastVO>,
) {
	companion object {
		val EMPTY = HomeUiModel(
			weather = WeatherVO(),
			forecast = listOf(
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
			)
		)
	}
}

fun HomeModel.toUiModel(): HomeUiModel {
	return HomeUiModel(
		weather = this.weather,
		forecast = this.forecast
	)
}