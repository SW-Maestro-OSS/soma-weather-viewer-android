package org.soma.weatherviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.usecase.GetHomeDataUseCase
import org.soma.weatherviewer.domain.model.WeatherVO
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeDataUseCase: GetHomeDataUseCase
): ViewModel() {

	private val lat: Float = 37.5683f
	private val lon: Float = 126.9778f

	val homeDataFlow = getHomeDataUseCase(lat, lon).stateIn(
		scope = viewModelScope,
		started = SharingStarted.Eagerly,
		initialValue = HomeModel(WeatherVO(), emptyList())
	)
}