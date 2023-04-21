package org.soma.weatherviewer.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : Fragment() {

	private lateinit var binding: FragmentSettingBinding
	val viewModel by viewModels<SettingViewModel>()

	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?
	): View {
		binding = FragmentSettingBinding.inflate(inflater, container, false)
		return binding.root
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		binding.lifecycleOwner = this@SettingFragment
		super.onViewCreated(view, savedInstanceState)

		initUI()
		subscribeData()
	}

	override fun onStart() {
		super.onStart()
		viewModel.getUserTempUnit()
	}

	private fun subscribeData() {
		lifecycleScope.launchWhenStarted {
			viewModel.unitTempStoreStatus.collectLatest { isSuccess ->
				if (!isSuccess) return@collectLatest

				Toast.makeText(requireContext(), "설정 완료", Toast.LENGTH_SHORT).show()
			}
		}

		lifecycleScope.launchWhenStarted {
			viewModel.userWeatherUnit.collectLatest { unit ->
				when (unit) {
					WeatherTempUnit.CELSIUS -> {
						initCelsiusArea()
					}
					WeatherTempUnit.FAHRENHEIT -> {
						initFahrenheitArea()
					}

					else -> {
						binding.settingFahrenheitArea.background = null
						binding.settingCelsiusArea.background = null
					}
				}
			}
		}
	}

	private fun initUI() {
		binding.settingCelsiusArea.setOnClickListener {
			initCelsiusArea()
			viewModel.storeUserTempUnit(WeatherTempUnit.CELSIUS)
		}

		binding.settingFahrenheitArea.setOnClickListener {
			initFahrenheitArea()
			viewModel.storeUserTempUnit(WeatherTempUnit.FAHRENHEIT)
		}
	}

	private fun initCelsiusArea() {
		binding.settingFahrenheitArea.background = null
		binding.settingCelsiusArea.background = ResourcesCompat.getDrawable(resources, R.drawable.bg_temp_unit_area, null)
	}

	private fun initFahrenheitArea() {
		binding.settingCelsiusArea.background = null
		binding.settingFahrenheitArea.background = ResourcesCompat.getDrawable(resources, R.drawable.bg_temp_unit_area, null)
	}

	override fun onDestroyView() {
		binding.unbind()
		super.onDestroyView()
	}

	companion object {
		@JvmStatic
		fun newInstance() = SettingFragment()
	}
}