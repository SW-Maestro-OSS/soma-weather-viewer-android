package org.soma.weatherviewer.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.common_ui.ForecastAdapter
import org.soma.weatherviewer.common_ui.ForecastSpaceItemDecoration
import org.soma.weatherviewer.domain.model.ForecastViewType
import org.soma.weatherviewer.forecast.databinding.FragmentForecastBinding

@AndroidEntryPoint
class ForecastFragment : Fragment() {

	private lateinit var binding: FragmentForecastBinding
	private val viewModel by viewModels<ForecastViewModel>()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentForecastBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.lifecycleOwner = this@ForecastFragment
		super.onViewCreated(view, savedInstanceState)

		with(binding) {
			vm = viewModel
			adapter = ForecastAdapter(ForecastViewType.LANDSCAPE)
			itemDecoration = ForecastSpaceItemDecoration(20, ForecastViewType.LANDSCAPE)
		}
	}

	override fun onStart() {
		super.onStart()
		viewModel.fetchForecast()
	}

	override fun onDestroyView() {
		binding.unbind()
		super.onDestroyView()
	}

	companion object {
		@JvmStatic
		fun newInstance() = ForecastFragment()
	}
}