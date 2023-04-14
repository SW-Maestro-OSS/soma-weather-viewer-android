package org.soma.weatherviewer.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.data.repository.WeatherRepositoryImpl
import org.soma.weatherviewer.domain.repository.WeatherRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

	@Binds
	fun bindsWeatherRepository(
		weatherRepositoryImpl: WeatherRepositoryImpl
	): WeatherRepository

}