package org.soma.weatherviewer.domain.datastore

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherTempUnits

interface WeatherViewerDataStore {

	suspend fun storeUserTempUnit(unit: WeatherTempUnits): Flow<Boolean>

	suspend fun getUserTempUnit(): WeatherTempUnits

}