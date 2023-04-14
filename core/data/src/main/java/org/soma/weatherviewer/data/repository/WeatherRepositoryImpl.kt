package org.soma.weatherviewer.data.repository

import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.flow.flow
import org.soma.weatherviewer.domain.repository.WeatherRepository
import org.soma.weatherviewer.data.datasource.WeatherDataSource
import org.soma.weatherviewer.data.model.mapper.asDomain
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
	private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

	override fun getCurrentWeather(
		lat: Float,
		lon: Float
	) = flow {
		val response = weatherDataSource.getCurrentWeather(lat = lat, lon = lon)
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

	override fun getCityWeather(
		cityName: String
	) = flow {
		val response = weatherDataSource.getCityWeather(cityName = cityName)
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

	override fun getForecast(
		lat: Float,
		lon: Float
	) = flow {
		val response = weatherDataSource.getForecast(lat = lat, lon = lon)
		response.suspendOnSuccess {
			emit(data.asDomain())
		}
	}

}