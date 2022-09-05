package org.soma.weatherviewer.common

import androidx.fragment.app.Fragment

interface MainActivityUtil {
    fun navigateToSettingFragment(fragment: Fragment)
    fun navigateToWeatherFragment(fragment: Fragment)
}