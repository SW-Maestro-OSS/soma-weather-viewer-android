package org.soma.weatherviewer.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.soma.weatherviewer.common.AppContainer
import org.soma.weatherviewer.common.HasAppContainer
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import javax.inject.Inject

@HiltAndroidApp
class SWVApplication : Application() {

}