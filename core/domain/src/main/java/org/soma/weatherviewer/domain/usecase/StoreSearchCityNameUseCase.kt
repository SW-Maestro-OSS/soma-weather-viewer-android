package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

/**
 * 사용자가 도시명을 검색 시 날씨정보를 가져오고 도시명을 저장하는 UseCase
 *
 * [WeatherRepository]에서 도시명에 대한 날씨정보를 못 가져올 시 없는 도시라고 판단하고 도시명을 저장하지 않습니다.
 * 만약 날씨정보를 불러왔다면 [WeatherDataStore]에 도시명을 저장합니다.
 */
class StoreSearchCityNameUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherDataStore: WeatherDataStore
) {
	suspend operator fun invoke(
		cityName: String,
		locale: Locale,
		onError: (String?) -> Unit
	) = flow {
		weatherDataStore.getUserTempUnit().collect { unit ->
			weatherRepository.getCityWeather(
				cityName = cityName,
				units = unit,
				locale = locale,
				onError
			).collect { weatherData ->
				weatherDataStore.storeSearchCityName(
					cityName,
				).collect {
					emit(weatherData)
				}
			}
		}
	}
}