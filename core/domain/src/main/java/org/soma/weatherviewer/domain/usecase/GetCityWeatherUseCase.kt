package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

/**
 * 도시명을 검색하여 해당 도시의 날씨 정보를 불러오는 UseCase
 *
 * [WeatherDataStore]에 저장된 기온 단위를 불러와 [WeatherRepository]의 units에 전달합니다.
 */
class GetCityWeatherUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherDataStore: WeatherDataStore
) {
	operator fun invoke(
		cityName: String = "seoul",
		locale: Locale,
		onError: (String?) -> Unit
	) = flow {
		weatherDataStore.getUserTempUnit().collect { unit ->
			weatherRepository.getCityWeather(
				cityName = cityName,
				units = unit,
				locale = locale,
				onError = onError
			).collect {
				emit(it)
			}
		}
	}
}