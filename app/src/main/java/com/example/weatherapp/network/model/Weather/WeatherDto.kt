package com.example.weatherapp.network.model.Weather

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("base")
    val base: String? = null,

    @SerializedName("visibility")
    val visibility: Int? = null,


    @SerializedName("timezone")
    val timezone: Int? = null,

    @SerializedName("id")
    val timeZoneId: Int? = null,

    @SerializedName("name")
    val name: String? = null,

    @SerializedName("weather")
     val  weather: List<WeatherInformation>? =  listOf(),

    @SerializedName("main")
    val main: MainInformation? = null,

    @SerializedName("wind")
    val wind: WindInformation? = null,

    @SerializedName("dt")
    val time: Long?=null,

    @SerializedName("sys")
    val sys: SysInformation? = null,

    @SerializedName("cod")
    val responseCode: String? = null,

    @SerializedName("message")
    val responseMessage: String? = null


    )

data class MainInformation(
    @SerializedName("temp")
    val temp: Double? = null,

    @SerializedName("feels_like")
    val feels_like: Double? = null,

    @SerializedName("temp_min")
    val temp_min: Double? = null,

    @SerializedName("temp_max")
    val temp_max: Double? = null,

    @SerializedName("pressure")
    val pressure: Double? = null,

    @SerializedName("humidity")
    val humidity: Double? = null

    )


data class WindInformation(
    @SerializedName("speed")
    val speed: Double? = null,

    @SerializedName("deg")
    val deg: Int? = null

)

data class SysInformation(
    @SerializedName("type")
    val type: Int? = null,

    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("country")
    val country: String? = null,

  @SerializedName("sunrise")
    val sunrise: Long? = null,

  @SerializedName("sunset")
    val sunset: Long? = null,


)
data class WeatherInformation(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("main")
    val main: String? = null,

    @SerializedName("description")
    val description: String? = null,

  @SerializedName("icon")
    val icon: String? = null,

)
