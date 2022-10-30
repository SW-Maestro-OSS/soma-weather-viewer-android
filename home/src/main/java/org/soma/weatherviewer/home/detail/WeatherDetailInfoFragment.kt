package org.soma.weatherviewer.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.soma.weatherviewer.common.HasAppContainer
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailInfoBinding

class WeatherDetailInfoFragment : Fragment() {

    private var _binding: FragmentWeatherDetailInfoBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WeatherDetailInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val weatherUseCase = (requireActivity() as HasAppContainer).appContainer.weatherUseCase
        viewModel = WeatherDetailInfoViewModel(weatherUseCase, arguments?.getInt(POSITION_KEY)?:0)

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

    companion object {
        private const val POSITION_KEY = "POSITION_KEY"
        fun newInstance(position: Int) : Fragment {
            val fragment = WeatherDetailInfoFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION_KEY, position)
            fragment.arguments = bundle
            return fragment
        }
    }
}