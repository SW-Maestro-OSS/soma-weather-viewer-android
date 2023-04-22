package org.soma.weatherviewer.forecast

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.common_ui.ForecastAdapter
import org.soma.weatherviewer.common_ui.ForecastSpaceItemDecoration
import org.soma.weatherviewer.common_ui.base.BaseFragment
import org.soma.weatherviewer.domain.model.ForecastViewType
import org.soma.weatherviewer.forecast.databinding.FragmentForecastBinding

@AndroidEntryPoint
class ForecastFragment : BaseFragment<FragmentForecastBinding>(R.layout.fragment_forecast) {

	private val viewModel by viewModels<ForecastViewModel>()

	override fun initView() {
		bind {
			vm = viewModel
			adapter = ForecastAdapter(ForecastViewType.LANDSCAPE)
			itemDecoration = ForecastSpaceItemDecoration(20, ForecastViewType.LANDSCAPE)
		}
	}

	override fun onStart() {
		super.onStart()
		viewModel.fetchForecast(getLocale())
	}
}