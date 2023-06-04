package hu.bme.aut.android.brewdogapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.android.brewdogapplication.data.Hops
import hu.bme.aut.android.brewdogapplication.data.Ingredients
import hu.bme.aut.android.brewdogapplication.data.Malt
import hu.bme.aut.android.brewdogapplication.database.Beer
import hu.bme.aut.android.brewdogapplication.database.BeerDao
import hu.bme.aut.android.brewdogapplication.database.BeerDatabase
import hu.bme.aut.android.brewdogapplication.network.BrewDogAPI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ExampleInstrumentedTest {
    private lateinit var beer: Beer
    private lateinit var dao: BeerDao
    private lateinit var db: BeerDatabase

    private val mockWebServer = MockWebServer()

    private val client = OkHttpClient.Builder()
        .connectTimeout(1, TimeUnit.SECONDS)
        .readTimeout(1, TimeUnit.SECONDS)
        .writeTimeout(1, TimeUnit.SECONDS)
        .build()

    private val api = Retrofit.Builder()
        .baseUrl(mockWebServer.url("/"))
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(BrewDogAPI::class.java)


    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun saveBeer() {
        initTestBeer()
        dao.insertBeer(beer)

        //get beerlist
        val storedBeerList = dao.getBeers()
        assertEquals(1, storedBeerList.size)
        val storedBeer = storedBeerList[0]
        assertEquals(beer, storedBeer)

    }

    private fun initTestBeer(){
        //init list of hops
        val hop1 = Hops("Simcoe", "bitter")
        val hop2 = Hops("Saaz", "flavour")
        val hops : List<Hops> = mutableListOf(hop1, hop2)

        //init list of malts
        val malt1 = Malt("CaraCrystal")
        val malt2 = Malt("Pale Ale")
        val malt : List<Malt> = mutableListOf(malt1, malt2)

        //init yeast
        val yeast = "Safale WB-06"
        val foodList : List<String> = mutableListOf("Hamburger")

        //init ingredient's collection
        val ingredients = Ingredients(malt, hops, yeast)

        beer = Beer(1, "Test IPA", "Test entity for room", "Test IPA Tagline", 5.0, 77, ingredients, foodList)
    }

    /*@After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }*/
}