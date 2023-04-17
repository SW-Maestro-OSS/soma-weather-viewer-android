package org.soma.weatherviewer.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {

	private lateinit var binding: FragmentSettingBinding

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

		binding.settingCelsiusArea.setOnClickListener {
			binding.settingFahrenheitArea.background = null
			binding.settingCelsiusArea.background = ResourcesCompat.getDrawable(resources, R.drawable.bg_temp_unit_area, null)
		}

		binding.settingFahrenheitArea.setOnClickListener {
			binding.settingCelsiusArea.background = null
			binding.settingFahrenheitArea.background = ResourcesCompat.getDrawable(resources, R.drawable.bg_temp_unit_area, null)
		}
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