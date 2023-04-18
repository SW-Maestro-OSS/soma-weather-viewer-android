package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import javax.inject.Inject

class StoreUserTempUnitUseCase @Inject constructor(
	private val weatherViewerDataStore: WeatherViewerDataStore
) {
	suspend operator fun invoke(unit: WeatherTempUnits) = flow {
		weatherViewerDataStore.storeUserTempUnit(unit).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}