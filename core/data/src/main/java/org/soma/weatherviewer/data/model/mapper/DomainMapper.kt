package org.soma.weatherviewer.data.model.mapper

interface DomainMapper<Domain, Data> {

	fun asDomain(data: Data): Domain
}