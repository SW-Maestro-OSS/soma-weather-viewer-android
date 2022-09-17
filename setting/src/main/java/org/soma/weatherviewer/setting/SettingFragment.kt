package org.soma.weatherviewer.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.MainActivityUtil
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment(), SettingFragmentListener {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    @Inject lateinit var dataStoreUseCase: DataStoreUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.listener = this
        }

        subscribeUi()

        return binding.root
    }

    private fun subscribeUi() {
        binding.tempOptionRg.setOnCheckedChangeListener { _, i ->
            lifecycleScope.launch {
                dataStoreUseCase.editTempType(
                    when (i) {
                        0 -> DataStoreUseCase.Companion.TempType.Celsius
                        else -> DataStoreUseCase.Companion.TempType.Fahrenheit
                    }
                )
            }
        }

        binding.homeOptionRg.setOnCheckedChangeListener { _, i ->
            lifecycleScope.launch {
                dataStoreUseCase.editHomeScreenOption(
                    when (i) {
                        0 -> DataStoreUseCase.Companion.HomeScreenOptionType.Current
                        else -> DataStoreUseCase.Companion.HomeScreenOptionType.FiveDays
                    }
                )
            }
        }
    }

    override fun onClickBackButton() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface SettingFragmentListener {
    fun onClickBackButton()
}