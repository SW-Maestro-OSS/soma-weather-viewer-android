package org.soma.weatherviewer.setting

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.common.util.HomeScreenOptionType
import org.soma.weatherviewer.common.util.TempType
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
    private val dataStoreUseCase: DataStoreUseCase
): ViewModel() {
    private val _tempType = MutableStateFlow(TempType.Celsius.name)
    val tempType: StateFlow<String> get() = _tempType

    private val _homeScreenOptionType = MutableStateFlow(HomeScreenOptionType.Current.name)
    val homeScreenOptionType: StateFlow<String> get() = _homeScreenOptionType

    init {
        viewModelScope.launch {
            _tempType.value = dataStoreUseCase.tempType.first()
            _homeScreenOptionType.value = dataStoreUseCase.homeScreenOption.first()
        }
    }
}