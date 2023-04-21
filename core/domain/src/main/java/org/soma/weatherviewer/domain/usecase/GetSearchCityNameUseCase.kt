package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import javax.inject.Inject

class GetSearchCityNameUseCase @Inject constructor(
	private val weatherViewerDataStore: WeatherViewerDataStore
) {
	operator fun invoke(): Flow<String> = flow {
		val searchCityName = weatherViewerDataStore.getSearchCityName().first()
		emit(searchCityName)
	}
}