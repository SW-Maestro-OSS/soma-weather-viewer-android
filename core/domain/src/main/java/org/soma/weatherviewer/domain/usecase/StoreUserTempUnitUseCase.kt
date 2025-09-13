package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * 사용자가 설정한 기온단위를 Local에 저장하는 UseCase
 */
class StoreUserTempUnitUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
) {
	operator fun invoke(unit: WeatherTempUnit): Flow<Boolean> =
		weatherRepository.storeUserTempUnit(unit)
}