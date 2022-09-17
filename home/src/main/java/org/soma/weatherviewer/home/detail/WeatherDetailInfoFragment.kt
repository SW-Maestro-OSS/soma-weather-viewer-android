package org.soma.weatherviewer.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.home.WeatherViewModel
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailInfoBinding

@AndroidEntryPoint
class WeatherDetailInfoFragment : Fragment() {

    private var _binding: FragmentWeatherDetailInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailInfoBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        initObservers()
        viewModel.getWeatherApi()

        return binding.root
    }

    private fun initObservers(){
        viewModel.weatherList.observe(viewLifecycleOwner, Observer {

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}