package org.soma.weatherviewer.common.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.common.repository.WeatherRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsWeatherRepository(
        repository: WeatherRepositoryImpl
    ) : WeatherRepository
}