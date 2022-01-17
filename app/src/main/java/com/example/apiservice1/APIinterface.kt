package com.example.apiservice1

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIInterface {
    @GET("forecast/city/{id}")
    suspend fun weatherInfo(@Path("id") id:String?): Data
}/*017010*/


data class Data(
    val title:String,
    val description: Map<String, String> ,
    val forecasts: List<Forecast>
)

data class Forecast(
    val telop: String
)
/*data class Weather(
    var title:String,
    var description:Description,
    var forecasts:Forecasts
)

data class Description(
    var headlineText:String
)
data class Forecasts(
    var date:String,
    var telop:String,
    var detail:Detail,
    var temperature:Temperature
)
data class Detail(
    var weather:String
)
data class Temperature(
    var min:String?,
    var max:String?
)*/