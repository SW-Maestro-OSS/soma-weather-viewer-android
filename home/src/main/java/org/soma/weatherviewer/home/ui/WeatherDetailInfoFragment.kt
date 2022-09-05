package org.soma.weatherviewer.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import org.soma.weatherviewer.home.R
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailInfoBinding

class WeatherDetailInfoFragment : Fragment() {

    private var _binding: FragmentWeatherDetailInfoBinding? = null
    private val binding get() = _binding!!

    private val viewModel: WeatherDetailInfoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeatherDetailInfoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}