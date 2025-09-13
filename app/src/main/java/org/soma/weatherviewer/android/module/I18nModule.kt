package org.soma.weatherviewer.android.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.soma.weatherviewer.android.i18n.I18NManagerImpl
import org.soma.weatherviewer.i18n.I18nManager
import javax.inject.Singleton

/**
 *
 * @author   JGeun
 * @created  2025/09/13
 */
@Module
@InstallIn(SingletonComponent::class)
object I18nModule {

	@Singleton
	@Provides
	fun provideI18nManager(
		@ApplicationContext applicationContext: Context
	): I18nManager {
		return I18NManagerImpl(applicationContext)
	}
}