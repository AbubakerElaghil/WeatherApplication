package com.example.weatherapp.presentation.ui.Weather

sealed class WeatherEvent {

    object WeatherByCity : WeatherEvent()

    object WeatherByLocation : WeatherEvent()

}
