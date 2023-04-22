package org.soma.weatherviewer.domain.translator

import java.util.*

object LangTranslator {
	fun getApiLang(locale: Locale): String {
		return when(locale) {
			Locale.KOREA -> "kr"
			else -> "en"
		}
	}
}