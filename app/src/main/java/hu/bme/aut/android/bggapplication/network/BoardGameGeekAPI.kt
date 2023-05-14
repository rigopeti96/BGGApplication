package hu.bme.aut.android.bggapplication.network

import android.util.Xml
import hu.bme.aut.android.bggapplication.data.GameDetailsData
import hu.bme.aut.android.bggapplication.data.SearchListByName
import hu.bme.aut.android.bggapplication.data.SearchListItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardGameGeekAPI {
    @GET("game/{id}")
    fun getGameDetailsData(
        @Path("id") id: Int
    ): Call<SearchListByName?>

    @GET("search/{name}")
    fun getGameList(
        @Query("name") name: String
    ): Call<ResponseBody?>

    @GET("collection/{username}")
    fun getCollectionList(
        @Path("username") username: String
    ): Call<ResponseBody?>
}