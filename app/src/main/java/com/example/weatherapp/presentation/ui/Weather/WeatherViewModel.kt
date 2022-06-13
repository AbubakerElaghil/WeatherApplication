package com.example.weatherapp.presentation.ui.Weather

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.repository.weather.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel()    {
     val weather: MutableState<Weather?> = mutableStateOf(null)
    val errorMessage: MutableState<String> = mutableStateOf("")
    val loading = mutableStateOf(false)
    val cityQuery: MutableState<String> = mutableStateOf("")
      var location: MutableState<Location?> = mutableStateOf(null)

    var alreadySentByLocationRequest= false

    fun onTriggerEvent(event: WeatherEvent) {
        viewModelScope.launch {
            loading.value = true
            errorMessage.value = ""
            weather.value = null
            try {
                when (event) {
                    is WeatherEvent.WeatherByCity -> {
                        getWeatherByCity()
                    }
                    is WeatherEvent.WeatherByLocation -> {
                        getWeatherByLocation()
                    }
                }
            } catch (e: Exception) {
                errorMessage.value = "city not found!"
            } finally {
                loading.value = false
            }


        }
    }

    private suspend fun getWeatherByLocation() {
        alreadySentByLocationRequest=true

        location.value?.let { location ->
            val weatherResult = weatherRepository.getWeatherByLocation(
                location.latitude.toString(),
                location.longitude.toString()
            )
            handleWeatherResponse(weatherResult)
        } ?: run { errorMessage.value = "no Location Found!." }

    }

    private suspend fun getWeatherByCity() {
        if (cityQuery.value.isNotEmpty()) {
            val weatherResult = weatherRepository.getWeatherByCity(cityQuery.value)
            handleWeatherResponse(weatherResult)
        } else {
            errorMessage.value = "Enter city to search!"
        }
    }

    fun onQueryChanged(query: String) {
        this.cityQuery.value = query
    }


    private fun handleWeatherResponse(weatherResult: Weather) {
        weatherResult.responseCode?.let { code ->
            if (code.equals("200")) {
                weather.value = weatherResult
                weatherResult.cityName?.let {
                    cityQuery.value= it
                }
                errorMessage.value = weatherResult.responseMessage!!
            }
        } ?: run { errorMessage.value = "failed to reach to server, Error Occurred!" }

    }

}