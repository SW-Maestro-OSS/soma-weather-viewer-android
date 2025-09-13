package org.soma.weatherviewer.data.repository

import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.soma.weatherviewer.data.api.WeatherApi
import org.soma.weatherviewer.data.datastore.WeatherDataStore
import org.soma.weatherviewer.data.dispatchers.Dispatcher
import org.soma.weatherviewer.data.dispatchers.WeatherViewerDispatchers
import org.soma.weatherviewer.data.model.mapper.asDomain
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.model.translateToAPIUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import org.soma.weatherviewer.domain.translator.LangTranslator
import org.soma.weatherviewer.i18n.I18nManager
import javax.inject.Inject

internal class WeatherRepositoryImpl @Inject constructor(
	private val weatherApi: WeatherApi,
	private val i18nManager: I18nManager,
	private val weatherDataStore: WeatherDataStore,
	@Dispatcher(WeatherViewerDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
) : WeatherRepository {

	/**
	 * 위도, 경도에 따라 오늘의 날씨(Weather) 정보를 얻는 기능
	 */
	override fun getCurrentWeather(coordinates: Coordinates): Flow<WeatherVO> = flow {
		val tempUnit = weatherDataStore.userTempUnitFlow.first()
		val response = weatherApi.getCurrentWeather(
			lat = coordinates.latitude.value,
			lon = coordinates.longitude.value,
			units = tempUnit.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(i18nManager.getSelectedRegion())
		)
		response.suspendOnSuccess {
			emit(data.asDomain(tempUnit, i18nManager.getSelectedRegion()))
		}
	}.flowOn(ioDispatcher)

	/**
	 * 해당 도시의 날씨 (Weather) 정보를 얻는 기능
	 */
	override fun getCityWeather(
		cityName: String,
		onError: (String?) -> Unit
	): Flow<WeatherVO> = flow {
		val tempUnit = weatherDataStore.userTempUnitFlow.first()
		val response = weatherApi.getCityWeather(
			cityName = cityName,
			units = tempUnit.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(i18nManager.getSelectedRegion())
		)
		response.suspendOnSuccess {
			emit(data.asDomain(tempUnit, i18nManager.getSelectedRegion()))
		}.onFailure { onError("올바르지 않은 도시이름 입니다") }
	}.flowOn(ioDispatcher)

	/**
	 * 위도, 경도에 따라 5일치 정보(Forecast)를 얻는 기능
	 */
	override fun getForecast(coordinates: Coordinates): Flow<List<ForecastVO>> = flow {
		val tempUnit = weatherDataStore.userTempUnitFlow.first()

		val response = weatherApi.getForecast(
			lat = coordinates.latitude.value,
			lon = coordinates.longitude.value,
			units = tempUnit.translateToAPIUnit(),
			lang = LangTranslator.getApiLang(i18nManager.getSelectedRegion())
		)
		response.suspendOnSuccess {
			emit(data.asDomain(tempUnit, i18nManager.getSelectedRegion()))
		}
	}.flowOn(ioDispatcher)

	override fun getSearchCityName(): Flow<String> {
		return weatherDataStore.searchCityNameFlow
	}

	override fun getUserTempUnit(): Flow<WeatherTempUnit> {
		return weatherDataStore.userTempUnitFlow
	}

	override fun storeUserTempUnit(unit: WeatherTempUnit): Flow<Boolean> = flow {
		emit(weatherDataStore.storeUserTempUnit(unit))
	}

	override fun storeSearchCityName(cityName: String): Flow<Boolean> = flow {
		emit(weatherDataStore.storeSearchCityName(cityName))
	}
}