package org.soma.weatherviewer.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import javax.inject.Inject

internal class WeatherDataStoreImpl @Inject constructor(
	private val dataStore: DataStore<Preferences>
): WeatherDataStore {

	/**
	 * 사용자의 temp unit을 저장합니다.
	 */
	override val userTempUnitFlow: Flow<WeatherTempUnit> =
		dataStore.data.map { pref ->
			val unitStr = pref[stringPreferencesKey(WeatherDataStore.TEMP_UNIT_KEY)] ?: ""
			if (unitStr.isEmpty()) WeatherTempUnit.CELSIUS else WeatherTempUnit.valueOf(unitStr.uppercase())
		}

	override suspend fun storeUserTempUnit(unit: WeatherTempUnit): Boolean {
		dataStore.edit { pref ->
			pref[stringPreferencesKey(WeatherDataStore.TEMP_UNIT_KEY)] = unit.name
		}

		return userTempUnitFlow.first() == unit
	}

	/**
	 * 사용자가 검색한 도시를 저장합니다.
	 */
	override val searchCityNameFlow: Flow<String> = dataStore.data.map { pref ->
		pref[stringPreferencesKey(WeatherDataStore.SEARCH_CITY_NAME_KEY)] ?: "seoul"
	}

	override suspend fun storeSearchCityName(cityName: String): Boolean {
		dataStore.edit { pref ->
			pref[stringPreferencesKey(WeatherDataStore.SEARCH_CITY_NAME_KEY)] = cityName
		}
		return searchCityNameFlow.first() == cityName
	}
}