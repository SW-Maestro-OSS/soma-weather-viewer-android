package org.soma.weatherviewer.domain.usecase

import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.datastore.WeatherDataStore
import org.soma.weatherviewer.domain.repository.WeatherRepository
import java.util.*
import javax.inject.Inject

/**
 * 위도, 경도를 통해 5일치 날씨 정보 (Forecast)를 불러오는 UseCase
 *
 * [WeatherDataStore]에 저장된 기온 단위를 불러와 [WeatherRepository]의 units에 전달합니다.
 */
class GetForecastUseCase @Inject constructor(
	private val weatherRepository: WeatherRepository,
	private val weatherDataStore: WeatherDataStore
){
	operator fun invoke(
		lat: Float,
		lon: Float,
		locale: Locale
	) = flow {
		weatherDataStore.getUserTempUnit().collect { unit ->
			weatherRepository.getForecast(
				lat = lat,
				lon = lon,
				units = unit,
				locale = locale
			).collect {
				emit(it)
			}
		}
	}
}