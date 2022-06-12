package com.example.weatherapp.repository.weather

import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.network.ApiService
import com.example.weatherapp.network.model.Weather.WeatherDto
import com.example.weatherapp.network.model.Weather.WeatherDtoMapper

class WeatherRepository_Impl(private val apiService: ApiService, private val weatherDtoMapper: WeatherDtoMapper, private val appId:String) : WeatherRepository {


    override suspend fun getWeatherByCity(city: String): Weather {
     return weatherDtoMapper.mapToDomainModel(apiService.getWeatherByCity(city,appId))
    }

    override suspend fun getWeatherByLocation(latitude: String, longitude: String): Weather {
        return  weatherDtoMapper.mapToDomainModel(apiService.getWeatherByLocation(latitude,longitude,appId))
    }
}