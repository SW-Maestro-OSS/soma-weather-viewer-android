package org.soma.weatherviewer.common.util

import android.content.Context
import androidx.core.content.res.ResourcesCompat
import org.soma.weatherviewer.common.data.WeatherDescription

fun WeatherDescription.convertRealName(context: Context): String {
    return context.getString(this.resId)
}
