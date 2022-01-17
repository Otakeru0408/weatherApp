package com.example.apiservice1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

val retrofit= Retrofit.Builder()
    .baseUrl("https://weather.tsukumijima.net/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build().create(APIInterface::class.java)

suspend fun getResponse(id:String): Data? {
    val response:Data
    try {
        response = retrofit.weatherInfo(id)
    }catch (e: Exception){
        return null
    }
    return response
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cities= mapOf<String,String>("函館" to "017010","仙台" to "040010","東京" to "130010","大阪" to "270000")
        val array= arrayOf("函館","仙台","東京","大阪")
        val adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,array)
        listView.adapter=adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            lifecycleScope.launch {
                val response = getResponse(cities[array[position]].toString())
                Log.d("response", response!!.description["headlineText"].toString())
                withContext(Dispatchers.Main) {
                    textView.text = response!!.forecasts[0].telop
                }
            }
        }
    }
}