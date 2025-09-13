package org.soma.weatherviewer.android.i18n

import android.content.Context
import org.soma.weatherviewer.i18n.I18nManager
import java.util.Locale

/**
 * @author   JGeun
 * @created  2025/09/13
 */
class I18NManagerImpl(
	private val applicationContext: Context
) : I18nManager {

	override fun getSelectedRegion(): Locale {
		return try {
			applicationContext.resources.configuration.locales[0]
		} catch (_: Exception) {
			Locale.getDefault()
		}
	}
}