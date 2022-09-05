package org.soma.weatherviewer.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.soma.weatherviewer.home.R
import org.soma.weatherviewer.home.databinding.FragmentWeatherBinding

class WeatherFragment : Fragment(), WeatherListFragmentListener {

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
        }

        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        viewModel.viewStatus.observe(this) {
            // TODO: 값에 따라 분기
            val fragment = WeatherDetailInfoFragment()
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

interface WeatherListFragmentListener {
    fun onClickBackButton()
    fun onClickSwitchButton()
}