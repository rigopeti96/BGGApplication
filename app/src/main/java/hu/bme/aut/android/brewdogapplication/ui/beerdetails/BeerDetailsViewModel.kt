package hu.bme.aut.android.brewdogapplication.ui.beerdetails

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.room.Room
import hu.bme.aut.android.brewdogapplication.data.BeerData
import java.util.ArrayList

class BeerDetailsViewModel : ViewModel() {
    private var beerDetailsLiveData = MutableLiveData<List<BeerData>>()
    private var beerId = MutableLiveData<Int>()


    fun getBeerDataList(): LiveData<List<BeerData>> {
        return beerDetailsLiveData
    }

    fun setBeerDataList(actBeerList: List<BeerData>){
        beerDetailsLiveData.value = actBeerList
    }

    fun getBeerId(): LiveData<Int> {
        Log.d("Beer id value", beerId.value.toString())
        return beerId
    }

    fun setBeerId(beerId: Int){
        this.beerId.value = beerId
        Log.d("Beer id value", this.beerId.value.toString())
    }
}