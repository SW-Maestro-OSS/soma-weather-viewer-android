package org.soma.weatherviewer.data.repository

import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.soma.weatherviewer.data.datasource.WeatherDataSource
import org.soma.weatherviewer.data.dispatchers.Dispatcher
import org.soma.weatherviewer.data.dispatchers.WeatherViewerDispatchers
import org.soma.weatherviewer.data.model.mapper.asDomain
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.translateToAPIUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import org.soma.weatherviewer.domain.translator.LangTranslator
import java.util.*
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
	private val weatherDataSource: WeatherDataSource,
	@Dispatcher(WeatherViewerDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {

	/**
	 * 위도, 경도에 따라 오늘의 날씨(Weather) 정보를 얻는 기능
	 */
	override fun getCurrentWeather(
		lat: Float,
		lon: Float,
		units: WeatherTempUnit,
		locale: Locale
	) = flow {
		val response = weatherDataSource.getCurrentWeather(
			lat = lat,
			lon = lon,
			units = units.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(locale)
		)
		response.suspendOnSuccess {
			emit(data.asDomain(units, locale))
		}
	}.flowOn(ioDispatcher)

	/**
	 * 해당 도시의 날씨 (Weather) 정보를 얻는 기능
	 */
	override fun getCityWeather(
		cityName: String,
		units: WeatherTempUnit,
		locale: Locale,
		onError: (String?) -> Unit
	) = flow {
		val response = weatherDataSource.getCityWeather(
			cityName = cityName,
			units = units.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(locale)
		)
		response.suspendOnSuccess {
			emit(data.asDomain(units, locale))
		}.onFailure { onError("올바르지 않은 도시이름 입니다") }
	}.flowOn(ioDispatcher)

	/**
	 * 위도, 경도에 따라 5일치 정보(Forecast)를 얻는 기능
	 */
	override fun getForecast(
		lat: Float,
		lon: Float,
		units: WeatherTempUnit,
		locale: Locale
	) = flow {
		val response = weatherDataSource.getForecast(
			lat = lat,
			lon = lon,
			units = units.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(locale)
		)
		response.suspendOnSuccess {
			emit(data.asDomain(units, locale))
		}
	}.flowOn(ioDispatcher)

}