package org.soma.weatherviewer.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.WeatherTempUnits
import org.soma.weatherviewer.domain.usecase.GetUserTempUnitUseCase
import org.soma.weatherviewer.domain.usecase.StoreUserTempUnitUseCase
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
	getUserTempUnitUseCase: GetUserTempUnitUseCase,
	private val storeUserTempUnitUseCase: StoreUserTempUnitUseCase
) : ViewModel() {

	private val _weatherUnitStoreStatus = MutableStateFlow(false)
	var unitTempStoreStatus = _weatherUnitStoreStatus.asStateFlow()

	private val _userWeatherUnit = MutableStateFlow(WeatherTempUnits.NONE)
	val userWeatherUnit = _userWeatherUnit.asStateFlow()

	init {
		viewModelScope.launch {
			_userWeatherUnit.value = getUserTempUnitUseCase()
		}
	}

	fun storeUserTempUnit(unit: WeatherTempUnits) {
		viewModelScope.launch {
			storeUserTempUnitUseCase(unit).collectLatest { isSuccess ->
				_weatherUnitStoreStatus.value = isSuccess

				// 저장하기 전 완료되는 상태를 확인하기 위해 StateFlow를 false로 설정
				_weatherUnitStoreStatus.value = false
			}
		}
	}
}