package hu.bme.aut.android.brewdogapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import hu.bme.aut.android.brewdogapplication.data.BeerData
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import hu.bme.aut.android.brewdogapplication.databinding.MainActivityBinding
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsFragment
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsViewModel
import hu.bme.aut.android.brewdogapplication.ui.beerlist.BeerListFragment
import hu.bme.aut.android.brewdogapplication.ui.beerlist.BeerListViewModel
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var beerListViewModel: BeerListViewModel
    private lateinit var beerDetailsViewModel: BeerDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        beerListViewModel = ViewModelProvider(this)[BeerListViewModel::class.java]
        beerDetailsViewModel = ViewModelProvider(this)[BeerDetailsViewModel::class.java]
        if (savedInstanceState == null) {
            changeToHome()
        }
    }

    fun changeToHome(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, MainFragment.newInstance())
            .commitNow()
    }

    fun changeToBeerList(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BeerListFragment.newInstance())
            .commitNow()
    }

    fun changeToBeerDatasheet(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BeerDetailsFragment.newInstance())
            .commitNow()
    }

    fun getBeerByFoodName(foodName: String){
        NetworkManager.getBeerListByFood(foodName).enqueue(object:
            Callback<List<BeerListData>?> {
            override fun onResponse(
                call: Call<List<BeerListData>?>,
                response: Response<List<BeerListData>?>
            ) {
                if(response.isSuccessful){
                    beerListViewModel.updateBeerListData(response.body()!!)
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(this@MainActivity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerListData>?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
        changeToBeerList()
    }

    fun getBeerByName(beerName: String){
        NetworkManager.getBeerListByName(beerName).enqueue(object:
            Callback<List<BeerListData>?> {
            override fun onResponse(
                call: Call<List<BeerListData>?>,
                response: Response<List<BeerListData>?>
            )
            {
                if(response.isSuccessful){
                    beerListViewModel.updateBeerListData(response.body()!!)
                    Log.d("Response size", response.body()!!.size.toString())
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(this@MainActivity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerListData>?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
        changeToBeerList()
    }

    fun getBeerData(beerId: Int){
        NetworkManager.getBeerById(beerId).enqueue(object:
            Callback<List<BeerData>?> {
            override fun onResponse(
                call: Call<List<BeerData>?>,
                response: Response<List<BeerData>?>
            )
            {
                if(response.isSuccessful){
                    beerDetailsViewModel.setBeerDataList(response.body()!!)

                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(this@MainActivity, "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerData>?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(this@MainActivity, "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
    }
}