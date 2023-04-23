package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import javax.inject.Inject

/**
 * [WeatherDataStore]에서 사용자가 검색했던 도시이름을 불러오는 UseCase
 */
class GetSearchCityNameUseCase @Inject constructor(
	private val weatherDataStore: WeatherDataStore
) {
	operator fun invoke(): Flow<String> = flow {
		val searchCityName = weatherDataStore.getSearchCityName().first()
		emit(searchCityName)
	}
}