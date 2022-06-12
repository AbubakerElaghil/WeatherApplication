package com.example.weatherapp.presentation.components

import android.graphics.Paint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.model.Weather
import java.text.SimpleDateFormat
import java.time.ZoneOffset
import java.util.*


@Composable
fun WeatherView(loading: Boolean, weather: Weather?, errorMessage: String) {

    Box() {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primary)
                .padding(top = 20.dp, start = 5.dp, end = 5.dp)

        ) {

            weather?.let { weather ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    weather.time?.let { time ->

                        val date = Date(time * 1000)
                        //  val offset= TimeZone.getTimeZone(weather.timezoneId.toString()).rawOffset
                        //    val adjustedDate = Date((date.time+ weather.timezoneId!!)*1000)
                        val dateformat = SimpleDateFormat("EEEE, dd MMMM")
                        val timeformat = SimpleDateFormat("hh:mm:ss")
                        Text(
                            text = dateformat.format(date),
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                }
                Spacer(
                    modifier = Modifier.padding(top = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    weather.time?.let { time ->

                        val date = Date(time * 1000)

                        val timeformat = SimpleDateFormat("HH:mm:ss")
                        Text(
                            text = timeformat.format(date),
                            fontSize = 25.sp,
                            color = Color.White
                        )
                    }

                }
                Spacer(
                    modifier = Modifier.padding(top = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    val cityAndCountry = "${weather.cityName}, ${weather.country} "
                    weather.cityName?.let { city ->
                        Text(
                            text = cityAndCountry,
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }

                }
                Spacer(
                    modifier = Modifier.padding(top = 15.dp)
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    weather.temprature?.let {
                        val temprature = it - 273.15
                        Text(
                            text = "${temprature.toInt().toString()} °C",
                            fontSize = 80.sp,
                            color = Color.White
                        )
                    }

                }
                Spacer(
                    modifier = Modifier.padding(top = 15.dp)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center

                ) {
                    val weatherDetail = "Feels like ${
                        weather.feelsLikeTemprature?.let {
                            (it - 273.15).toInt().toString()
                        }
                    }°C. ${weather.mainWeather}, ${weather.description} "
                    Text(
                        text = weatherDetail,
                        fontSize = 14.sp,
                        color = Color.White
                    )
                }
                Spacer(
                    modifier = Modifier.padding(top = 35.dp)
                )

                SetWeatherDetailView(weather)

            }
        }
        CircularProgressBar(isLoading = loading)
        CheckErrorMessage(errorMessage)
    }


}

@Composable
fun SetWeatherDetailView(weather: Weather) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(6.dp)
                .width(110.dp)
            ,
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Wind Speed",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary,
                        fontWeight = Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = "${weather.windSpeed} m/s",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.padding(start = 10.dp)
        )
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(6.dp) .width(110.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Humidity",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary,
                        fontWeight = Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = "${weather.humidity} %",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier.padding(start = 10.dp)
        )
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(6.dp) .width(110.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Visibility",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary,
                        fontWeight = Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = "${weather.visibility} km",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }


    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center

    ) {
        Card(
            shape = MaterialTheme.shapes.small,
            modifier = Modifier
                .padding(6.dp) .width(110.dp),
            elevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center
                )
                {
                    Text(
                        text = "Pressure",
                        fontSize = 15.sp,
                        color = MaterialTheme.colors.primary,
                        fontWeight = Bold
                    )
                }
                Row(
                    modifier = Modifier
                        .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.Center
                )
                {

                    Text(
                        text = "${weather.pressure} hPa",
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }


}

@Composable
fun CheckErrorMessage(errorMessage: String) {
    Log.e("WeatherView", "errorMessage:  $errorMessage")
    if (errorMessage.isNotEmpty()) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = errorMessage,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onError
            )
        }
    } else {

    }
}
