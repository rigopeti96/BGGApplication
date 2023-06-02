package hu.bme.aut.android.brewdogapplication.ui.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.brewdogapplication.data.BeerData
import java.util.ArrayList

class BeerDetailsViewModel : ViewModel() {
    private var beerDetailsLiveData = MutableLiveData<List<BeerData>>()

    fun getBeerDataList(): LiveData<List<BeerData>> {
        return beerDetailsLiveData
    }

    fun setBeerDataList(actBeerList: List<BeerData>){
        beerDetailsLiveData.value = actBeerList
    }
}