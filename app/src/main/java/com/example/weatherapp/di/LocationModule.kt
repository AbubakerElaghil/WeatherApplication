package com.example.weatherapp.di

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.repository.location.request.LocationRepository
import com.example.weatherapp.repository.location.request.LocationRepository_Impl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped


@Module
@InstallIn(ActivityComponent::class)
    class LocationModule    {


    @ActivityScoped
    @Provides
    fun provideActivity(@ActivityContext context: Context) : AppCompatActivity {
        return context as AppCompatActivity
    }


   @ActivityScoped
    @Provides
    fun provideLocation(activity : AppCompatActivity  )  : LocationRepository {
        return LocationRepository_Impl(activity  )
    }



}