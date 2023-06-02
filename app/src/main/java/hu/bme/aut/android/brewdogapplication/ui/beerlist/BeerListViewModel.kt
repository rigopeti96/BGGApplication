package hu.bme.aut.android.brewdogapplication.ui.beerlist

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import java.util.ArrayList

class BeerListViewModel : ViewModel() {
    private var beerListLiveData = MutableLiveData<List<BeerListData>>()

    fun getBeerDataList(): LiveData<List<BeerListData>> {

        return beerListLiveData
    }

    fun setBeerDataList(actBeerList: List<BeerListData>){
        beerListLiveData.value = actBeerList
        Log.d("live data size", beerListLiveData.value!!.size.toString())
    }
}