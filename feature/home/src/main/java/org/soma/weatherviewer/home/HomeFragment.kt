package org.soma.weatherviewer.home

import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.common_ui.ForecastAdapter
import org.soma.weatherviewer.common_ui.ForecastSpaceItemDecoration
import org.soma.weatherviewer.common_ui.base.BaseFragment
import org.soma.weatherviewer.domain.model.ForecastViewType
import org.soma.weatherviewer.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

	private val viewModel by viewModels<HomeViewModel>()

	override fun initView() {
		bind {
			vm = viewModel
			adapter = ForecastAdapter(ForecastViewType.PORTRAIT)
			itemDecoration = ForecastSpaceItemDecoration(30, ForecastViewType.PORTRAIT)
		}
	}

	override fun onStart() {
		super.onStart()

		viewModel.fetchHomeData(getLocale())
	}
}