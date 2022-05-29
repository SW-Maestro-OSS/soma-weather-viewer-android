package org.soma.weatherviewer.common_ui

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout

class WeatherView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {
    companion object {
        fun testMethod1() {
            print("TestMethod1")
        }
    }
}