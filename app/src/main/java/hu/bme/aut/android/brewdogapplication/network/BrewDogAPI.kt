package hu.bme.aut.android.brewdogapplication.network

import hu.bme.aut.android.brewdogapplication.data.*
import retrofit2.Call
import retrofit2.http.*

interface BrewDogAPI {
    @GET("beers/{id}")
    fun getBeerDetailsData(
        @Path("id") id: Int
    ): Call<List<BeerData>?>

    @GET("beers")
    fun getBeerListByName(
        @Query("beer_name") name: String
    ): Call<List<BeerListData>?>

    @GET("beers")
    fun getBeerListByFood(
        @Query("food") foodName: String
    ): Call<List<BeerListData>?>
}