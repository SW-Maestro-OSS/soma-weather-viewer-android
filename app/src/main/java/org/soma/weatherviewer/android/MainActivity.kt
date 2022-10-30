package org.soma.weatherviewer.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.android.databinding.ActivityMainBinding
import org.soma.weatherviewer.common.AppContainer
import org.soma.weatherviewer.common.HasAppContainer
import org.soma.weatherviewer.common.MainActivityUtil
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.home.HomeFragmentDirections
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainActivityUtil, HasAppContainer {
    @Inject lateinit var dataStoreUseCase: DataStoreUseCase
    override lateinit var appContainer: AppContainer

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appContainer = AppContainer(dataStoreUseCase)

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