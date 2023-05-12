package hu.bme.aut.android.bggapplication.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val boardGameGeekAPI: BoardGameGeekAPI

    private const val SERVICE_URL = "https://boardgamegeek.com/xmlapi/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        boardGameGeekAPI = retrofit.create(BoardGameGeekAPI::class.java)
    }
}