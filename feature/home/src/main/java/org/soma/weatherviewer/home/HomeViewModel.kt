package org.soma.weatherviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.Coordinates
import org.soma.weatherviewer.domain.model.Latitude
import org.soma.weatherviewer.domain.model.Longitude
import org.soma.weatherviewer.domain.usecase.GetHomeDataUseCase
import org.soma.weatherviewer.home.model.HomeUiModel
import org.soma.weatherviewer.home.model.toUiModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

	private val _homeDataFlow = MutableStateFlow(HomeUiModel.EMPTY)
	val homeDataFlow = _homeDataFlow.asStateFlow()

	fun fetchHomeData() {
		viewModelScope.launch {
			getHomeDataUseCase(defaultCoordinates).collectLatest {
				_homeDataFlow.value = it.toUiModel()
			}
		}
	}

	companion object {
		private val defaultCoordinates = Coordinates(
			latitude = Latitude(37.5683f),
			longitude = Longitude(126.9778f)
		)
	}
}