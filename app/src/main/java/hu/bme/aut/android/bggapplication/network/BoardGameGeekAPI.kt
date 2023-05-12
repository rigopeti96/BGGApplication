package hu.bme.aut.android.bggapplication.network

import hu.bme.aut.android.bggapplication.data.GameDetailsData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardGameGeekAPI {
    @GET("boardgame/{id}")
    fun getGameDetailsData(
        @Path("id") id: Int,
        @Body collectionPointPutData: GameDetailsData
    ): Call<Any?>
}