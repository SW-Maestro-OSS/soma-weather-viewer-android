package org.soma.weatherviewer.domain.model.mapper

import org.soma.weatherviewer.domain.model.WeatherTempUnit
import java.util.*

/**
 * [DomainMapper]에서는 각 mapper에서 꼭 필요한 함수를 정의하게 됩니다.
 * 각각의 mapper는 [DomainMapper]를 상속 받음으로서 정해진 함수를 override 하여 필수 함수들이 누락 됨을 방지합니다
 */
interface DomainMapper<Domain, Data> {

	fun asDomain(data: Data, unit: WeatherTempUnit, locale: Locale): Domain
}