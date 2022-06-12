package com.example.weatherapp.network

import com.example.weatherapp.network.model.Weather.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("weather")
    suspend fun getWeatherByCity(@Query("q") city:String,@Query("appid")appId:String ): WeatherDto

    @GET("weather")
    suspend fun getWeatherByLocation(@Query("lat") latitude:String,@Query("lon") longitude:String,@Query("appid")appId:String ): WeatherDto


}