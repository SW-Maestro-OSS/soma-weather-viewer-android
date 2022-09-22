package org.soma.weatherviewer.android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.android.databinding.ActivityMainBinding
import org.soma.weatherviewer.common.MainActivityUtil
import org.soma.weatherviewer.home.HomeFragmentDirections

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityUtil {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    override fun navigateToSettingFragment(fragment: Fragment) {
        val directions = HomeFragmentDirections.actionHomeFragmentToSettingFragment()
        fragment.findNavController().navigate(directions)
    }

    override fun navigateToWeatherFragment(fragment: Fragment) {
        val directions = HomeFragmentDirections.actionHomeFragmentToWeatherFragment()
        fragment.findNavController().navigate(directions)
    }
}