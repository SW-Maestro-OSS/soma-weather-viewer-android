package org.soma.weatherviewer.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.soma.weatherviewer.home.databinding.FragmentWeatherListInfoBinding

class WeatherListInfoFragment : Fragment() {

    private var _binding: FragmentWeatherListInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherListInfoBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
            it.adapter = WeatherAdapter()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}