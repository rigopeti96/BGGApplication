package hu.bme.aut.android.brewdogapplication.ui.beerdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hu.bme.aut.android.brewdogapplication.data.BeerData
import java.util.ArrayList

class BeerDetailsViewModel : ViewModel() {
    private var BeerListLiveData= MutableLiveData<ArrayList<BeerData>>()

    fun getBeerDataList(): LiveData<ArrayList<BeerData>> {
        return BeerListLiveData
    }

    //fun setBeerDataList()
}