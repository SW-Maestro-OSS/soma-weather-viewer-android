package org.soma.weatherviewer.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.soma.weatherviewer.data.dispatchers.Dispatcher
import org.soma.weatherviewer.data.dispatchers.WeatherViewerDispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule  {

	@Provides
	@Dispatcher(WeatherViewerDispatchers.IO)
	fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}