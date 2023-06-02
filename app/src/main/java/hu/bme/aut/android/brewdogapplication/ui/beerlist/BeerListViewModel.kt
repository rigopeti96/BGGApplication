package hu.bme.aut.android.brewdogapplication.ui.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import java.util.ArrayList
import android.os.Handler
import android.os.Looper

class BeerListViewModel : ViewModel() {
    private val _beerListLiveData = MutableLiveData<List<BeerListData>>()
    val beerListData: LiveData<List<BeerListData>>
        get() = _beerListLiveData
    fun updateBeerListData(beerListData: List<BeerListData>) {
        Handler(Looper.getMainLooper()).postDelayed({
            _beerListLiveData.postValue(beerListData)
        }, 5000)
    }
}