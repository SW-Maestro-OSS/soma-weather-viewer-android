package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

/**
 * 위도, 경도를 통해 오늘의 날씨와 5일치 날씨 정보를 불러와 HomeModel로 정제하는 UseCase
 *
 * [WeatherDataStore]에 저장된 기온 단위를 불러와 [WeatherRepository]의 units에 전달합니다.
 */
class GetHomeDataUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherDataStore: WeatherDataStore
) {
	suspend operator fun invoke(
		lat: Float,
		lon: Float,
		locale: Locale
	): Flow<HomeModel> = flow {

		weatherDataStore.getUserTempUnit().collect { unit ->
			combine(
				weatherRepository.getCurrentWeather(
					lat = lat,
					lon = lon,
					units = unit,
					locale = locale
				),
				weatherRepository.getForecast(
					lat = lat,
					lon = lon,
					units = unit,
					locale = locale
				),
			) { weather, forecast ->
				HomeModel(weather, forecast)
			}.collect {
				emit(it)
			}
		}
	}
}