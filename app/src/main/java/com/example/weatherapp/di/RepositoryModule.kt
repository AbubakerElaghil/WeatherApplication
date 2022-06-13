package com.example.weatherapp.di

import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.model.Weather.WeatherDtoMapper
  import com.example.weatherapp.repository.weather.WeatherRepository
import com.example.weatherapp.repository.weather.WeatherRepository_Impl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule{


   @Singleton
    @Provides
    fun provideWeatherRepository(apiService: ApiService, weatherDtoMapper: WeatherDtoMapper ,@Named("app_id") appId:String ) :WeatherRepository{
        return WeatherRepository_Impl(apiService,weatherDtoMapper,appId )
    }





}