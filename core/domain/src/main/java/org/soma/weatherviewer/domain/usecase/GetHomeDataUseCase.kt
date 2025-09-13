package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * 위도, 경도를 통해 오늘의 날씨와 5일치 날씨 정보를 불러와 HomeModel로 정제하는 UseCase
 */
class GetHomeDataUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository
) {
	operator fun invoke(
		coordinates: Coordinates
	): Flow<HomeModel> = combine(
		weatherRepository.getCurrentWeather(coordinates),
		weatherRepository.getForecast(coordinates),
	) { weather, forecast ->
		HomeModel(weather, forecast)
	}
}