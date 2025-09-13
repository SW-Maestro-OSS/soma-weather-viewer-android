package org.soma.weatherviewer.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.data.datastore.WeatherDataStoreImpl
import org.soma.weatherviewer.data.datastore.WeatherDataStore
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

	private const val DATASTORE_NAME = "WEATHER_VIEWER_DATSTORE"

	private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATASTORE_NAME)

	@Singleton
	@Provides
	fun provideDataStore(@ApplicationContext context: Context): WeatherDataStore {
		return WeatherDataStoreImpl(context.dataStore)
	}
}