package org.soma.weatherviewer.domain.model.mapper

import org.soma.weatherviewer.domain.model.WeatherTempUnit
import java.util.*

interface DomainMapper<Domain, Data> {

	fun asDomain(data: Data, unit: WeatherTempUnit, locale: Locale): Domain
}