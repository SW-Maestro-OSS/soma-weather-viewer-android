package org.soma.weatherviewer.data.di

import com.skydoves.sandwich.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.soma.weatherviewer.data.datasource.WeatherDataSource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

	@Provides
	@Singleton
	fun provideRequestHttpLoggingInterceptor() : HttpLoggingInterceptor {
		return HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(interceptor: HttpLoggingInterceptor) : OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
		return Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideWeatherDataSource(retrofit: Retrofit): WeatherDataSource {
		return retrofit.create(WeatherDataSource::class.java)
	}
}