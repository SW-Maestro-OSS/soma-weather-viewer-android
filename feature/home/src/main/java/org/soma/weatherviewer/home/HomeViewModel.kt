package org.soma.weatherviewer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.soma.weatherviewer.domain.model.ForecastVO
import org.soma.weatherviewer.domain.model.HomeModel
import org.soma.weatherviewer.domain.model.WeatherVO
import org.soma.weatherviewer.domain.usecase.GetHomeDataUseCase
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val getHomeDataUseCase: GetHomeDataUseCase
) : ViewModel() {

	private val lat: Float = 37.5683f
	private val lon: Float = 126.9778f

	private val _homeDataFlow = MutableStateFlow(
		HomeModel(
			WeatherVO(),
			listOf(
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
				ForecastVO(),
			)
		)
	)
	val homeDataFlow = _homeDataFlow.asStateFlow()

	fun fetchHomeData(locale: Locale) {
		viewModelScope.launch {
			getHomeDataUseCase(lat, lon, locale).collectLatest {
				_homeDataFlow.value = it
			}
		}
	}
}