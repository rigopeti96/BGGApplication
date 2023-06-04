package hu.bme.aut.android.brewdogapplication

import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.android.brewdogapplication.data.BeerData
import hu.bme.aut.android.brewdogapplication.data.Hops
import hu.bme.aut.android.brewdogapplication.data.Ingredients
import hu.bme.aut.android.brewdogapplication.data.Malt
import hu.bme.aut.android.brewdogapplication.database.BeerDao
import hu.bme.aut.android.brewdogapplication.database.BeerDatabase
import hu.bme.aut.android.brewdogapplication.exception.EmptyEditTextException
import hu.bme.aut.android.brewdogapplication.network.BrewDogAPI
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.ExpectedException
import org.junit.runner.RunWith
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class ExampleInstrumentedTest {
    private lateinit var beer: BeerData
    private lateinit var dao: BeerDao
    private lateinit var db: BeerDatabase

    private val mockWebServer = MockWebServer()

    @Before
    fun initBeer(){
        initTestBeer()
    }

    @Test
    fun getASingleBeer(){
        val actual = NetworkManager.getBeerById(121).execute()
        val expected = listOf(beer)
        assertEquals(200, actual.code())
        assertEquals(expected[0].id, actual.body()!![0].id)
        assertEquals(expected[0].name, actual.body()!![0].name)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    private fun initTestBeer(){
        //init list of hops
        val hop1 = Hops("Ahtanum", "bitter")
        val hop2 = Hops("Chinook", "bitter")
        val hop3 = Hops("Crystal", "flavour")
        val hop4 = Hops("Motueka", "flavour")
        val hops : List<Hops> = mutableListOf(hop1, hop2, hop3, hop4)

        //init list of malts
        val malt1 = Malt("Extra Pale")
        val malt : List<Malt> = mutableListOf(malt1)

        //init yeast
        val yeast = "Wyeast 3522 - Belgian Ardennes™"
        val foodList : List<String> = mutableListOf("Moules frites","Thai green curry","Lemon posset")

        //init ingredient's collection
        val ingredients = Ingredients(malt, hops, yeast)

        beer = BeerData(121,
            "Punk Monk",
            "Old world meets new in this trans-atlantic mash up of brewing ingredients. Belgian funk incorporates itself into the pithy, resin and grapefruit, with pineapple and banana rounding out a rambunctious avalanche of fruity hops.",
            "Who Ordered The Belgian Yeast?",
            6.0,
            60,
            ingredients,
            foodList
        )
    }
}