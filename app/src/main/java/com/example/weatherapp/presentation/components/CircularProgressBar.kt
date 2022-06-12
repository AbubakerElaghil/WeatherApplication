package com.example.weatherapp.presentation.components

 import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
 import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.unit.dp


@Composable
fun CircularProgressBar(isLoading : Boolean){
    if(isLoading){
    Column(modifier = Modifier
        .padding(20.dp)
        .fillMaxSize(),
         verticalArrangement = Arrangement.Center,
         horizontalAlignment = Alignment.CenterHorizontally
    ){
        CircularProgressIndicator(
            color = Color.White
        )
    }
   }

}