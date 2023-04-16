package org.soma.weatherviewer.domain.model.mapper

import java.util.*

interface DomainMapper<Domain, Data> {

	fun asDomain(data: Data, locale: Locale): Domain
}