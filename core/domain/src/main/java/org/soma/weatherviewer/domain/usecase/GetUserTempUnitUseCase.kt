package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetUserTempUnitUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
){
	operator fun invoke(): Flow<WeatherTempUnit> =
		weatherRepository.getUserTempUnit()
}