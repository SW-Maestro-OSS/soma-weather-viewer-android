package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import javax.inject.Inject

/**
 * 사용자가 설정한 기온단위를 [WeatherDataStore]에 저장하는 UseCase
 */
class StoreUserTempUnitUseCase @Inject constructor(
	private val weatherDataStore: WeatherDataStore
) {
	suspend operator fun invoke(unit: WeatherTempUnit) = flow {
		weatherDataStore.storeUserTempUnit(unit).collect { isSuccess ->
			emit(isSuccess)
		}
	}
}