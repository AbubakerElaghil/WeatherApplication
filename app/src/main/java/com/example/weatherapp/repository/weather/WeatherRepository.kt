package com.example.weatherapp.repository.weather

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.network.model.Weather.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherRepository {
    suspend fun getWeatherByCity( city:String  ): Weather
    suspend fun getWeatherByLocation(@Query("lat") latitude:String, @Query("lon") longitude:String  ): Weather
}