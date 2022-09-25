package org.soma.weatherviewer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.home.databinding.FragmentWeatherBinding
import org.soma.weatherviewer.home.detail.WeatherDetailFragment
import org.soma.weatherviewer.home.detail.WeatherDetailInfoFragment
import org.soma.weatherviewer.home.list.WeatherListInfoFragment

@AndroidEntryPoint
class WeatherFragment : Fragment(), WeatherFragmentListener {

    private var _binding: FragmentWeatherBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.listener = this
        }
        viewModel.getWeatherApi()
        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        viewModel.viewStatus.observe(viewLifecycleOwner) {
            val fragment =
                if (viewModel.viewStatus.value == 0) WeatherDetailFragment.newInstance()
                else WeatherListInfoFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onClickBackButton() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onClickSwitchButton() {
        viewModel.switchViewStatus()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface WeatherFragmentListener {
    fun onClickBackButton()
    fun onClickSwitchButton()
}