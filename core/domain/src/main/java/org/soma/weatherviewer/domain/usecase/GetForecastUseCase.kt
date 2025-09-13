package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * 위도, 경도를 통해 5일치 날씨 정보 (Forecast)를 불러오는 UseCase
 */
class GetForecastUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
) {
	operator fun invoke(coordinates: Coordinates): Flow<List<ForecastVO>> =
		weatherRepository.getForecast(coordinates)
}