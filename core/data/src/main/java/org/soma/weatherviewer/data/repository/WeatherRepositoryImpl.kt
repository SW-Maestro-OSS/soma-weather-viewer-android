package org.soma.weatherviewer.data.repository

import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.data.datasource.WeatherDataSource
import org.soma.weatherviewer.data.model.mapper.asDomain
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.model.translateToAPIUnit
import org.soma.weatherviewer.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
	private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

	override fun getCurrentWeather(
		lat: Float,
		lon: Float,
		units: WeatherTempUnits
	) = flow {
		val response = weatherDataSource.getCurrentWeather(lat = lat, lon = lon, units = units.translateToAPIUnit())
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

	override fun getCityWeather(
		cityName: String,
		units: WeatherTempUnits
	) = flow {
		val response = weatherDataSource.getCityWeather(cityName = cityName, units = units.translateToAPIUnit())
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

	override fun getForecast(
		lat: Float,
		lon: Float,
		units: WeatherTempUnits
	) = flow {
		val response = weatherDataSource.getForecast(lat = lat, lon = lon, units = units.translateToAPIUnit())
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

}