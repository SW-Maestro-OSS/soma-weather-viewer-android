package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class GetSearchCityNameUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
) {
	operator fun invoke(): Flow<String> =
		weatherRepository.getSearchCityName()
}