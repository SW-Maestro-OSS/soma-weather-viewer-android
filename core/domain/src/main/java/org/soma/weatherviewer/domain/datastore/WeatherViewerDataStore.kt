package org.soma.weatherviewer.domain.datastore

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherTempUnit

interface WeatherViewerDataStore {

	suspend fun storeUserTempUnit(unit: WeatherTempUnit): Flow<Boolean>

	suspend fun getUserTempUnit(): Flow<WeatherTempUnit>

}