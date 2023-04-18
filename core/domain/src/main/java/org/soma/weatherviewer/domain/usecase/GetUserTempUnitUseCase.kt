package org.soma.weatherviewer.domain.usecase

import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import javax.inject.Inject

class GetUserTempUnitUseCase @Inject constructor(
	private val weatherViewerDataStore: WeatherViewerDataStore
){
	suspend operator fun invoke(): WeatherTempUnits {
		return weatherViewerDataStore.getUserTempUnit()
	}
}