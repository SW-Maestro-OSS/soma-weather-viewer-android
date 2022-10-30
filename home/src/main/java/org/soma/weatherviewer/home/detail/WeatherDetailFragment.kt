package org.soma.weatherviewer.home.detail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import org.soma.weatherviewer.home.WeatherViewModel
import org.soma.weatherviewer.home.databinding.FragmentWeatherDetailBinding

class WeatherDetailFragment : Fragment(){

    private var _binding: FragmentWeatherDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<WeatherViewModel>(ownerProducer = { requireParentFragment() })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeatherDetailBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.viewModel = viewModel
        }


        binding.weatherDetailViewpager.apply {
            adapter = WeatherDetailAdapter(this@WeatherDetailFragment)
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }

//        initObservers()

        return binding.root
    }

//    private fun initObservers(){
//        viewModel.weatherList.observe(viewLifecycleOwner) {
//
//        }
//    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() : Fragment = WeatherDetailFragment()
    }
}