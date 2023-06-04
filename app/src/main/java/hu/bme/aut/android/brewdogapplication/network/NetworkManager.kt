package hu.bme.aut.android.brewdogapplication.network

import hu.bme.aut.android.brewdogapplication.data.BeerData
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val BREW_DOG_API: BrewDogAPI

    private const val SERVICE_URL = "https://api.punkapi.com/v2/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        BREW_DOG_API = retrofit.create(BrewDogAPI::class.java)
    }

    fun getBeerById(id: Int): Call<List<BeerData>>{
        return BREW_DOG_API.getBeerDetailsData(id)
    }

    fun getBeerListByName(name: String): Call<List<BeerListData>?>{
        return BREW_DOG_API.getBeerListByName(name)
    }

    fun getBeerListByFood(foodName: String): Call<List<BeerListData>?>{
        return BREW_DOG_API.getBeerListByFood(foodName)
    }
}