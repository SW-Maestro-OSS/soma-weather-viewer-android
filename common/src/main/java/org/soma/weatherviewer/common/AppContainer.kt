package org.soma.weatherviewer.common

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.soma.weatherviewer.common.domain.usecase.DataStoreUseCase
import org.soma.weatherviewer.common.domain.usecase.WeatherUseCase
import org.soma.weatherviewer.common.network.WeatherService
import org.soma.weatherviewer.common.repository.WeatherRepository
import org.soma.weatherviewer.common.repository.WeatherRepositoryImpl
import org.soma.weatherviewer.common.util.DATASTORE_NAME
import org.soma.weatherviewer.foundation.NetworkModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

/**
 * 수동 DI를 보여주기 위해, 이미 Hilt에 의해 provide되고 있는 값들도 이 클래스에서 다시 정의하게 됩니다. 단, 같은 이름으로
 * 여러 [DataStore]가 정의될 경우 오류가 발생하므로, dataStoreUseCase는 수동으로 처리하지 않는 예외로 두고 있습니다.
 */
class AppContainer(dataStoreUseCase: DataStoreUseCase) {
    private val interceptor = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(NetworkModule.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    private val weatherRepository = WeatherRepositoryImpl(weatherService)

    val weatherUseCase = WeatherUseCase(weatherRepository, dataStoreUseCase)
}