package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherViewerDataStore
import javax.inject.Inject

class GetUserTempUnitUseCase @Inject constructor(
	private val weatherViewerDataStore: WeatherViewerDataStore
){
	suspend operator fun invoke() = flow {
		 weatherViewerDataStore.getUserTempUnit().collect {
			emit(it)
		}
	}
}