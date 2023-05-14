package hu.bme.aut.android.bggapplication.network

import android.util.Xml
import hu.bme.aut.android.bggapplication.data.SearchListByName
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object NetworkManager {
    private val retrofit: Retrofit
    private val boardGameGeekAPI: BoardGameGeekAPI

    private const val SERVICE_URL = "https://boardgamegeek.com/xmlapi/"

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
        boardGameGeekAPI = retrofit.create(BoardGameGeekAPI::class.java)
    }

    fun getGameDetailsData(id: Int): Call<SearchListByName?>{
        return boardGameGeekAPI.getGameDetailsData(id)
    }

    fun getGameList(name: String): Call<ResponseBody?>{
        return boardGameGeekAPI.getGameList(name)
    }

    fun getCollectionList(username: String): Call<ResponseBody?>{
        return boardGameGeekAPI.getCollectionList(username)
    }
}