package com.example.weatherapp.di

import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.model.Weather.WeatherDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule{
   const val baseUrl="https://api.openweathermap.org/data/2.5/"
    @Singleton
    @Provides
    fun provideWeatherMapper() :WeatherDtoMapper{
        return WeatherDtoMapper()
    }

    @Singleton
    @Provides
    @Named("app_id")
    fun provideAppId() :String{
        return "043f097207d3478580d872370604b9f0"
    }


    @Singleton
    @Provides
    fun provideRecipeService() : ApiService {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()
            .create(ApiService::class.java)
    }

}