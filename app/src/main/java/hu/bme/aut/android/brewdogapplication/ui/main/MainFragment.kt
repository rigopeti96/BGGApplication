package hu.bme.aut.android.brewdogapplication.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import hu.bme.aut.android.brewdogapplication.MainActivity
import hu.bme.aut.android.brewdogapplication.R
import hu.bme.aut.android.brewdogapplication.adapter.BeerListAdapter
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import hu.bme.aut.android.brewdogapplication.databinding.MainFragmentBinding
import hu.bme.aut.android.brewdogapplication.exception.EmptyEditTextException
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsFragment
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsViewModel

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var beerDetailsViewModel: BeerDetailsViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BeerListAdapter
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(activity as MainActivity)[MainViewModel::class.java]
        beerDetailsViewModel = ViewModelProvider(activity as MainActivity)[BeerDetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        firebaseAnalytics = Firebase.analytics
        binding.btnFoodName.setOnClickListener {
            try {
                if(binding.etFoodName.text.isEmpty()){
                    throw EmptyEditTextException("Food name edit text is empty!")
                }
                getBeerByFoodName(binding.etFoodName.text.toString())
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, binding.etFoodName.text.toString())
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle)
            } catch (e: EmptyEditTextException){
                Toast.makeText(requireContext(), resources.getText(R.string.food_name_error), Toast.LENGTH_LONG).show()
            }

        }

        binding.btnBeerName.setOnClickListener {
            try {
                if(binding.etBeerName.text.isEmpty()){
                    throw EmptyEditTextException("Beer name edit text is empty!")
                }
                getBeerByName(binding.etBeerName.text.toString())
            } catch (e: EmptyEditTextException){
                Toast.makeText(requireContext(), resources.getText(R.string.beer_name_error), Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun getBeerByFoodName(foodName: String){
        NetworkManager.getBeerListByFood(foodName).enqueue(object:
            Callback<List<BeerListData>?> {
            override fun onResponse(
                call: Call<List<BeerListData>?>,
                response: Response<List<BeerListData>?>
            ) {
                if(response.isSuccessful){
                    for(i in 0 until response.body()!!.size){
                        adapter.addBeerItem(response.body()!![i])
                    }
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerListData>?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(requireContext(), "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
    }

    private fun getBeerByName(beerName: String){
        NetworkManager.getBeerListByName(beerName).enqueue(object:
            Callback<List<BeerListData>?> {
            override fun onResponse(
                call: Call<List<BeerListData>?>,
                response: Response<List<BeerListData>?>
            )
            {
                if(response.isSuccessful){
                    for(i in 0 until response.body()!!.size){
                        adapter.addBeerItem(response.body()!![i])
                    }
                    Log.d("Response size", response.body()!!.size.toString())
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<BeerListData>?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(requireContext(), "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
    }
    private fun initRecyclerView() {
        recyclerView = binding.incRecycleView.rvBeerRecyclerView
        adapter = BeerListAdapter(activity as MainActivity, beerDetailsViewModel)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()).apply { }
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}