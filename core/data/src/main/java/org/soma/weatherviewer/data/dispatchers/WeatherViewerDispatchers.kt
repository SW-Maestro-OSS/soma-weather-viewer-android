package org.soma.weatherviewer.data.dispatchers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val weatherViewerDispatchers: WeatherViewerDispatchers)

enum class WeatherViewerDispatchers {
	IO
}