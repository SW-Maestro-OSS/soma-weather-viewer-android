package org.soma.weatherviewer.common.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.common.repository.WeatherRepositoryImpl
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsWeatherRepository(
        repository: WeatherRepositoryImpl
    ) : WeatherRepository
}