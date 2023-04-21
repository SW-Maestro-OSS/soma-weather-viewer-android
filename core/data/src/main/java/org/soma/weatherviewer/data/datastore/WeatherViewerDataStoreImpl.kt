package org.soma.weatherviewer.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.soma.weatherviewer.domain.datastore.SEARCH_CITY_NAME_KEY
import org.soma.weatherviewer.domain.datastore.TEMP_UNIT_KEY
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import javax.inject.Inject

class WeatherViewerDataStoreImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>
): WeatherViewerDataStore {

	/**
	 * 사용자의 temp unit을 저장합니다.
	 */
	private val userTempUnitFlow: Flow<WeatherTempUnit> =
		dataStore.data.map { pref ->
			val unitStr = pref[stringPreferencesKey(TEMP_UNIT_KEY)] ?: ""
			if (unitStr.isEmpty()) WeatherTempUnit.CELSIUS else WeatherTempUnit.valueOf(unitStr.uppercase())
		}

	override suspend fun storeUserTempUnit(unit: WeatherTempUnit) = flow {
		try {
			dataStore.edit { pref ->
				pref[stringPreferencesKey(TEMP_UNIT_KEY)] = unit.name
				emit(true)
			}
		} catch(e: Exception) {
			emit(false)
		}
	}

	override suspend fun getUserTempUnit(): Flow<WeatherTempUnit> = flow {
		val unit = userTempUnitFlow.first()
		emit(unit)
	}

	/**
	 * 사용자의 City을 저장합니다.
	 */
	private val searchCityNameFlow: Flow<String> =
		dataStore.data.map { pref ->
			val cityName = pref[stringPreferencesKey(SEARCH_CITY_NAME_KEY)] ?: ""
			cityName.ifEmpty { "seoul" }
		}
	override suspend fun storeSearchCityName(cityName: String) = flow {
		try {
			dataStore.edit { pref ->
				pref[stringPreferencesKey(SEARCH_CITY_NAME_KEY)] = cityName
				emit(true)
			}
		} catch(e: Exception) {
			emit(false)
		}
	}

	override suspend fun getSearchCityName() = flow {
		val searchCityName = searchCityNameFlow.first()
		emit(searchCityName)
	}

}