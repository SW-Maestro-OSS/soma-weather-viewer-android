package org.soma.weatherviewer.setting

import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import org.soma.weatherviewer.common_ui.base.BaseFragment
import org.soma.weatherviewer.domain.model.WeatherTempUnit
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

	private val viewModel by viewModels<SettingViewModel>()

	override fun initView() {
		initUIEvent()
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

				Toast.makeText(requireContext(), R.string.setup_message, Toast.LENGTH_SHORT).show()
			}
		}

		lifecycleScope.launchWhenStarted {
			viewModel.userWeatherUnit.collectLatest { unit ->
				when (unit) {
					WeatherTempUnit.CELSIUS -> initCelsiusArea()
					WeatherTempUnit.FAHRENHEIT -> initFahrenheitArea()
					else -> {
						binding.settingFahrenheitArea.background = null
						binding.settingCelsiusArea.background = null
					}
				}
			}
		}
	}

	private fun initUIEvent() {
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
}