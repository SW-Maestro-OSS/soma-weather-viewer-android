package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

/**
 * 사용자가 도시명을 검색 시 날씨정보를 가져오고 도시명을 저장하는 UseCase
 *
 * [WeatherRepository]에서 도시명에 대한 날씨정보를 못 가져올 시 없는 도시라고 판단하고 도시명을 저장하지 않습니다.
 * 만약 날씨정보를 불러왔다면 Local에 도시명을 저장합니다.
 */
@OptIn(ExperimentalCoroutinesApi::class)
class StoreSearchCityNameUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
) {
	operator fun invoke(
		cityName: String,
		onError: (String?) -> Unit
	): Flow<WeatherVO> = weatherRepository.getCityWeather(
		cityName = cityName,
		onError = onError
	).flatMapLatest { weatherData ->
		val storeSearchCityNameSuccess = weatherRepository.storeSearchCityName(cityName).first()
		if (storeSearchCityNameSuccess) {
			flowOf(weatherData)
		} else {
			emptyFlow()
		}
	}
}