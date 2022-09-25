package org.soma.weatherviewer.home.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.soma.weatherviewer.common.HasAppContainer
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailInfoBinding

class WeatherDetailInfoFragment : Fragment() {

    private var _binding: FragmentWeatherDetailInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WeatherDetailInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val weatherUseCase = (requireActivity() as HasAppContainer).appContainer.weatherUseCase
        viewModel = WeatherDetailInfoViewModel(weatherUseCase)

        _binding = FragmentWeatherDetailInfoBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }

        viewModel.getWeatherApi()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}