package com.example.weatherapp.network.model.Weather

 import com.example.weatherapp.domain.model.Weather
 import com.example.weatherapp.domain.util.DomainMapper
 import javax.inject.Inject
import javax.inject.Named

class WeatherDtoMapper : DomainMapper<WeatherDto, Weather> {

    /**
     * function to convert from network model to domain model ( Ui model)
     * Dto = Data transfer object
     */
    override fun mapToDomainModel(model: WeatherDto ): Weather {

        return Weather(
            time = model.time,
            timezone = model.timezone,
            timezoneId = model.timeZoneId,
            mainWeather = model.weather?.let {
                if(!it.isEmpty())
                    it[0].main
                    else ""
         }?:"",
            description = model.weather?.let {
                if(!it.isEmpty())
                    it[0].description
                else ""
            }?:"",
            temprature = model.main?.temp ?:0.0,
            feelsLikeTemprature = model.main?.feels_like ?:0.0,
            tempratureMin = model.main?.temp_min ?:0.0,
            tempratureMax =  model.main?.temp_max ?:0.0,
            pressure =  model.main?.pressure ?:0.0,
            humidity =  model.main?.humidity ?:0.0,
            windSpeed =  model.wind?.speed ?:0.0,
            windDegree =  model.wind?.deg ?:0,
            country = model.sys?.country?:"",
            cityName =  model.name?:"",
            visibility =  model.visibility?:0,
            responseCode =  model.responseCode?:"404",
            responseMessage =  model.responseMessage?:"",
            )
    }

    /**
     * function to convert from domain model to network model
     * maybe will be used in the future if object are sent to the server...
     */
    override fun mapFromDomainModel(domainModel: Weather): WeatherDto {

        return WeatherDto()

    }

    fun ToDomainList(initial :List<WeatherDto> ): List<Weather> {
        return initial.map { mapToDomainModel(it) }
    }
   fun fromDomainList(initial :List<Weather> ): List<WeatherDto> {
        return initial.map { mapFromDomainModel(it) }
    }



}