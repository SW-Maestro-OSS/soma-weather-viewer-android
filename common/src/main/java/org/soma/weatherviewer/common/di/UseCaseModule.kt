package org.soma.weatherviewer.common.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase
import org.soma.weatherviewer.common.repository.WeatherRepository
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideWeatherUseCase(
        weatherRepository: WeatherRepository,
        dataStoreUseCase: DataStoreUseCase
    ) = WeatherUseCase(weatherRepository, dataStoreUseCase)
}