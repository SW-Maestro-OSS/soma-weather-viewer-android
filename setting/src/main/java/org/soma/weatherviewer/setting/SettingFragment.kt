package org.soma.weatherviewer.setting

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.common.util.HomeScreenOptionType
import org.soma.weatherviewer.common.util.TempType
import org.soma.weatherviewer.setting.databinding.FragmentSettingBinding
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment : Fragment(), SettingFragmentListener {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingViewModel by viewModels()

    @Inject lateinit var dataStoreUseCase: DataStoreUseCase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false).also {
            it.lifecycleOwner = this
            it.listener = this
            it.viewModel = viewModel
        }

        subscribeUI()

        return binding.root
    }

    /*
    * TODO DataBinding으로 코드 리팩토링 진행
    */
    private fun subscribeUI() {
        lifecycleScope.launchWhenCreated {
            viewModel.tempType.collectLatest {
                Log.d("SettingViewModel", "subscribeUI - tempType: $it")
                if (it == TempType.Celsius.name) {
                    binding.tempOptionCelsiusRb.isChecked = true
                } else {
                    binding.tempOptionFahrenheitRb.isChecked = true
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.homeScreenOptionType.collectLatest {
                Log.d("SettingViewModel", "subscribeUI - homeOption: $it")
                if (it == HomeScreenOptionType.Current.name) {
                    binding.homeOptionCurrentRb.isChecked = true
                } else {
                    binding.homeOptionListRb.isChecked = true
                }
            }
        }
    }

    override fun onClickBackButton() {
        activity?.onBackPressedDispatcher?.onBackPressed()
    }

    override fun onClickCelsiusButton() {
        lifecycleScope.launch {
            dataStoreUseCase.editTempType(TempType.Celsius)
        }
    }

    override fun onClickFahrenheitButton() {
        lifecycleScope.launch {
            dataStoreUseCase.editTempType(TempType.Fahrenheit)
        }
    }

    override fun onClickCurrentButton() {
        lifecycleScope.launch {
            dataStoreUseCase.editHomeScreenOption(HomeScreenOptionType.Current)
        }
    }

    override fun onClickFiveDaysButton() {
        lifecycleScope.launch {
            dataStoreUseCase.editHomeScreenOption(HomeScreenOptionType.FiveDays)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}

interface SettingFragmentListener {
    fun onClickBackButton()
    fun onClickCelsiusButton()
    fun onClickFahrenheitButton()
    fun onClickCurrentButton()
    fun onClickFiveDaysButton()
}