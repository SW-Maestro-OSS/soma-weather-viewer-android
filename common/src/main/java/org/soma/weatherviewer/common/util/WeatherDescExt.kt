package org.soma.weatherviewer.common.util

import android.content.Context
import org.soma.weatherviewer.common.domain.model.WeatherDescription

fun WeatherDescription.convertRealName(context: Context): String {
    return context.getString(this.resId)
}
