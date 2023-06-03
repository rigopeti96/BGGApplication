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
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainActivityBinding
    private lateinit var beerDetailsViewModel: BeerDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
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

    fun changeToBeerDatasheet(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, BeerDetailsFragment.newInstance())
            .commitNow()
    }
}