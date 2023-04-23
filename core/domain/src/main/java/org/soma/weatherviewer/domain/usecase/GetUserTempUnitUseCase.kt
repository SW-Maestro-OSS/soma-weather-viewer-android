package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import javax.inject.Inject

/**
 * [WeatherDataStore]에서 사용자가 설정한 기온 단위를 가져오는 UseCase
 */
class GetUserTempUnitUseCase @Inject constructor(
	private val weatherDataStore: WeatherDataStore
){
	suspend operator fun invoke() = flow {
		 weatherDataStore.getUserTempUnit().collect {
			emit(it)
		}
	}
}