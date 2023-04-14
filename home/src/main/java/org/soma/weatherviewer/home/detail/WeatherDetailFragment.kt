package org.soma.weatherviewer.home.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.common.HasAppContainer
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment(){

    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: WeatherDetailInfoViewModel
    private var detailSize: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
        }

        val appContainer = (requireActivity() as HasAppContainer).appContainer
        val weatherUseCase = appContainer.weatherUseCase
        viewModel = WeatherDetailInfoViewModel(weatherUseCase)


        lifecycleScope.launchWhenCreated {
            viewModel.weather.collectLatest {
                val detailSize = arguments?.getInt("detailSize") ?: 5

                if (it.isNotEmpty()) {
                    binding.weatherDetailViewpager.apply {
                        adapter = WeatherDetailAdapter(this@WeatherDetailFragment, detailSize, it).apply {
                            addItemDecoration(DetailInfoItemDecoration())
                        }
                        orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() : Fragment = WeatherDetailFragment()
    }
}