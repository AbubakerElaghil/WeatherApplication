package com.example.weatherapp.presentation.ui.Weather

import android.location.Location
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.presentation.components.SearchAppBar
import com.example.weatherapp.presentation.components.WeatherView
 import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import com.example.weatherapp.repository.location.request.LocationRepository
import com.example.weatherapp.repository.location.request.LocationRepository_Impl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherFragment  : Fragment()  {

    @Inject
    lateinit var locationRepository: LocationRepository

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listenToLocation()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        locationRepository.startLocationRequest()
    }

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
        checkLocation(viewModel.location.value)

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

    private fun checkLocation(location: Location?) {
        location?.let {
            if(!viewModel.alreadySentByLocationRequest)
            viewModel.onTriggerEvent(WeatherEvent.WeatherByLocation)
        }
    }

    fun listenToLocation() {
      (locationRepository as LocationRepository_Impl).setLocationState(viewModel.location)
    }

}