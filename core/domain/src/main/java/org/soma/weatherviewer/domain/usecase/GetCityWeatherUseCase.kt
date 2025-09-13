package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * 도시명을 검색하여 해당 도시의 날씨 정보를 불러오는 UseCase
 */
class GetCityWeatherUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
) {
	operator fun invoke(
		cityName: String,
		onError: (String?) -> Unit
	): Flow<WeatherVO> = weatherRepository.getCityWeather(
		cityName = cityName,
		onError = onError
	)
}