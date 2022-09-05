package org.soma.weatherviewer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.soma.weatherviewer.common.MainActivityUtil

class MainActivity : AppCompatActivity(), MainActivityUtil {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToSettingFragment() {

    }

    override fun navigateToHomeFragment() {
        TODO("Not yet implemented")
    }
}