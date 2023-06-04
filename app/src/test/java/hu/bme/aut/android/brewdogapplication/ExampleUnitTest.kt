package hu.bme.aut.android.brewdogapplication

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import hu.bme.aut.android.brewdogapplication.data.Hops
import hu.bme.aut.android.brewdogapplication.data.Ingredients
import hu.bme.aut.android.brewdogapplication.data.Malt
import hu.bme.aut.android.brewdogapplication.database.Beer
import hu.bme.aut.android.brewdogapplication.database.BeerDao
import hu.bme.aut.android.brewdogapplication.database.BeerDatabase
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import kotlinx.coroutines.flow.toList
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import retrofit2.Retrofit
import java.io.IOException

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    private lateinit var beer: Beer
    private lateinit var dao: BeerDao
    private lateinit var db: BeerDatabase

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

}