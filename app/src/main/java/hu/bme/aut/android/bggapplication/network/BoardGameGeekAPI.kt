package hu.bme.aut.android.bggapplication.network

import android.util.Xml
import hu.bme.aut.android.bggapplication.data.GameDetailsData
import hu.bme.aut.android.bggapplication.data.SearchListByName
import hu.bme.aut.android.bggapplication.data.SearchListItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface BoardGameGeekAPI {
    @GET("game/{id}")
    @Headers("Content-Type: application/xml; charset=utf-8")
    fun getGameDetailsData(
        @Path("id") id: Int
    ): Call<SearchListByName?>

    @GET("search")
    @Headers("Content-Type: application/xml; charset=utf-8")
    fun getGameList(
        @Query("search") name: String
    ): Call<ResponseBody?>

    @GET("collection/{username}")
    @Headers("Content-Type: application/xml; charset=utf-8")
    fun getCollectionList(
        @Path("username") username: String
    ): Call<ResponseBody?>
}