package com.example.weatherapp.presentation.ui.Weather

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.IntentSender.SendIntentException
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.presentation.components.SearchAppBar
import com.example.weatherapp.presentation.components.WeatherView
import com.example.weatherapp.presentation.ui.baseFragment.BaseLocationFragment
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherFragment : BaseLocationFragment() {

    private val viewModel: WeatherViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                SetWeatherView()
            }
        }
    }



    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    private fun SetWeatherView() {
        val cityQuery = viewModel.cityQuery.value
        val loading = viewModel.loading.value
        val weather = viewModel.weather.value
        val errorMessage = viewModel.errorMessage.value
        WeatherAppTheme(false) {

            Column() {

                Scaffold(
                    topBar = {
                        SearchAppBar(
                            query = cityQuery,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = {
                                viewModel.onTriggerEvent(WeatherEvent.WeatherByCity)
                            }
                        )
                    }
                ) {
                    WeatherView(loading = loading, weather = weather, errorMessage)

                }
            }
        }

    }

    override fun locationIsReady(location: Location) {
        if(viewModel.location.value==null) {
            viewModel.location.value = location
            viewModel.onTriggerEvent(WeatherEvent.WeatherByLocation)
        }
    }

}