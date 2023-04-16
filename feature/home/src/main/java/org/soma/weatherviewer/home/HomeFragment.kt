package org.soma.weatherviewer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import org.soma.weatherviewer.common_ui.ForecastAdapter
import org.soma.weatherviewer.domain.model.ForecastViewType
import org.soma.weatherviewer.common_ui.SpaceItemDecoration
import org.soma.weatherviewer.home.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : Fragment() {

	private lateinit var binding: FragmentHomeBinding
	private val viewModel by viewModels<HomeViewModel>()
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentHomeBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.lifecycleOwner = this@HomeFragment
		super.onViewCreated(view, savedInstanceState)

		with(binding) {
			vm = viewModel
			adapter = ForecastAdapter(ForecastViewType.PORTRAIT)
			itemDecoration = SpaceItemDecoration(30)
		}
	}

	override fun onDestroyView() {
		binding.unbind()
		super.onDestroyView()
	}

	companion object {
		@JvmStatic
		fun newInstance() = HomeFragment()
	}
}