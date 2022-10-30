package org.soma.weatherviewer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.common.MainActivityUtil
import org.soma.weatherviewer.common.util.HomeScreenOptionType
import org.soma.weatherviewer.home.databinding.FragmentHomeBinding
import org.soma.weatherviewer.home.detail.WeatherDetailFragment

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeFragmentListener {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<WeatherViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@HomeFragment
            listener = this@HomeFragment
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.getHomeData()

        lifecycleScope.launchWhenStarted {
            viewModel.homeScreenOptionType.collectLatest {
                val detailSize = if (it == HomeScreenOptionType.Current.name) 1 else 5

                val bundle = Bundle().apply {
                    putInt("detailSize", detailSize)
                }

                childFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, WeatherDetailFragment().apply {
                        arguments = bundle
                    }).commit()
            }
        }
    }

    override fun onClick5DaysButton() {
        (activity as MainActivityUtil).navigateToWeatherFragment(this)
    }

    override fun onClickSettingButton() {
        (activity as MainActivityUtil).navigateToSettingFragment(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {

    }
}

interface HomeFragmentListener {
    fun onClick5DaysButton()
    fun onClickSettingButton()
}