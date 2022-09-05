package org.soma.weatherviewer.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.foundation.NetworkUtil
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("WeatherRepository")
    fun provideWeatherRepository(): WeatherRepository {
        return NetworkUtil.create(WeatherRepository::class.java)
    }
}