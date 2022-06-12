package com.example.weatherapp.domain.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val time: Long?=null,
    val timezone: Int?=null,
    val timezoneId: Int?=null,
    val mainWeather:String?=null,
    val description :String?=null,
    val temprature:Double?=null,
    val feelsLikeTemprature:Double?=null,
    val tempratureMin:Double?=null,
    val tempratureMax:Double?=null,
    val pressure:Double?=null,
    val humidity:Double?=null,
    val windSpeed:Double?=null,
    val windDegree:Int?=null,
    val country:String?=null,
     val cityName:String?=null,
     val visibility:Int?=null,
     val responseCode: String? = null,
     val responseMessage: String? = null


)
