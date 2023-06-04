package hu.bme.aut.android.brewdogapplication.ui.beerdetails

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import hu.bme.aut.android.brewdogapplication.MainActivity
import hu.bme.aut.android.brewdogapplication.data.BeerData
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import hu.bme.aut.android.brewdogapplication.data.Hops
import hu.bme.aut.android.brewdogapplication.data.Malt
import hu.bme.aut.android.brewdogapplication.databinding.BeerDetailsFragmentBinding
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BeerDetailsFragment : Fragment() {
    private lateinit var viewModel: BeerDetailsViewModel
    private lateinit var binding: BeerDetailsFragmentBinding
    private var actBeerId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BeerDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(activity as MainActivity)[BeerDetailsViewModel::class.java]

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actBeerId = viewModel.getBeerId().value!!
        getBeerData(actBeerId)
        binding.tvBackButton.setOnClickListener {
            (activity as MainActivity).changeToHome()
        }
    }

    companion object {
        fun newInstance() = BeerDetailsFragment()
    }

    private fun getBeerData(beerId: Int){
        NetworkManager.getBeerById(beerId).enqueue(object:
            Callback<List<BeerData>> {
            override fun onResponse(
                call: Call<List<BeerData>>,
                response: Response<List<BeerData>>
            )
            {
                if(response.isSuccessful){
                    viewModel.setBeerDataList(response.body()!!)
                    fullFillDatasheet()
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(requireActivity(), "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerData>>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(requireActivity(), "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
    }

    @SuppressLint("SetTextI18n")
    private fun fullFillDatasheet(){
        val beerData = viewModel.getBeerDataList().value!![0]
        binding.tvBeerTagline.text = beerData.tagline
        binding.tvBeerName.text = beerData.name
        binding.tvBeerIbu.text = beerData.ibu.toString()
        binding.tvBeerAbv.text = "${beerData.abv} %"
        binding.tvBeerHops.text = createHopsTextview(beerData.ingredients.hops)
        binding.tvBeerMalt.text = createMaltTextview(beerData.ingredients.malt)
        binding.tvBeerYeast.text = beerData.ingredients.yeast
        binding.tvBeerFood.text = createFoodlistTextview(beerData.food_pairing)
        binding.tvBeerDescription.text = beerData.description
    }

    private fun createHopsTextview(hops: List<Hops>): String{
        var listOfHops = ""
        for(i in hops.indices){
            listOfHops = if(listOfHops ==""){
                hops[i].name
            } else {
                "$listOfHops, ${hops[i].name}"
            }
        }

        return listOfHops
    }

    private fun createMaltTextview(malt: List<Malt>): String{
        var listOfMalt = ""
        for(i in malt.indices){
            listOfMalt = if(listOfMalt ==""){
                malt[i].name
            } else {
                "$listOfMalt, ${malt[i].name}"
            }
        }

        return listOfMalt
    }

    private fun createFoodlistTextview(foods: List<String>): String{
        var listOfMalt = ""
        for(i in foods.indices){
            listOfMalt = if(listOfMalt ==""){
                foods[i]
            } else {
                "$listOfMalt, ${foods[i]}"
            }
        }

        return listOfMalt
    }
}