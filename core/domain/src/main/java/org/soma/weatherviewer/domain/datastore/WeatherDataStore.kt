package org.soma.weatherviewer.domain.datastore

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherTempUnit

interface WeatherDataStore {

	/**
	 * 사용자가 선택한 기온단위를 관리하는 기능
	 */
	suspend fun storeUserTempUnit(unit: WeatherTempUnit): Flow<Boolean>

	suspend fun getUserTempUnit(): Flow<WeatherTempUnit>

	/**
	 * 사용자가 검색한 도시 이름을 관리하는 기능
	 */
	suspend fun storeSearchCityName(cityName: String): Flow<Boolean>

	suspend fun getSearchCityName(): Flow<String>

}