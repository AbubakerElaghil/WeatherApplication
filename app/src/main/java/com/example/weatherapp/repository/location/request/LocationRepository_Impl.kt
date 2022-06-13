package com.example.weatherapp.repository.location.request

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.IntentSender
import android.location.Location
import android.location.LocationManager
import android.os.Looper
import android.util.Log
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import javax.inject.Inject

class LocationRepository_Impl @Inject constructor (var activity: AppCompatActivity  ) : LocationRepository {

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit  var locationManager: LocationManager
    private lateinit var locationValue: MutableState<Location?>

    fun setLocationState(  locationValue: MutableState<Location?>){
        this.locationValue=locationValue
    }

    var locationSettingsRequest =  activity.registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
              getLastLocation()
        }
    }
    var locationPermissionRequest =  activity.registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                 startLocationProcess()
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                 startLocationProcess()
            } else -> { // No location access granted.

        }
        }
    }

  override  fun startLocationRequest()  {
         locationPermissionRequest.launch(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION))

    }

      fun startLocationProcess() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        if(isLocationEnabled()){
            getLastLocation()
        }else {
            requestEnableLocation()
        }
    }

    private fun requestEnableLocation() {
        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        val result = LocationServices.getSettingsClient(activity).checkLocationSettings(builder.build())
        result.addOnCompleteListener(OnCompleteListener { task->
            try {
                task.getResult<ApiException>(ApiException::class.java)
                getLastLocation()
            } catch (exception: ApiException) {
                when (exception.statusCode) {
                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                        try {
                            val resolvable = exception as ResolvableApiException
                            locationSettingsRequest.launch(IntentSenderRequest.Builder(resolvable.resolution).build())

                        } catch (e: IntentSender.SendIntentException) {
                            // Ignore the error.
                        } catch (e: ClassCastException) {
                            //Toast.makeText(requireContext(),e.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE -> {}
                }
            }
        })

    }

    @SuppressLint("MissingPermission")
      fun getLastLocation() {
        fusedLocationClient.lastLocation.addOnSuccessListener(activity,  OnSuccessListener<Location?> { location -> // Got last known location. In some rare situations this can be null.
            location?.let {location ->
                if(locationValue.value==null) {
                    locationValue.value = location
                }

            }
        })
        val locationRequest: LocationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 1000
        var  locationCallback : LocationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    location?.let {location ->
                        if(locationValue.value==null) {
                            locationValue.value = location
                        }
                    }
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.myLooper()
        )

    }

    private fun isLocationEnabled(): Boolean {
        locationManager  =activity.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)
    }

}