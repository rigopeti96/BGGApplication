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
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import hu.bme.aut.android.brewdogapplication.databinding.MainFragmentBinding
import hu.bme.aut.android.brewdogapplication.network.NetworkManager

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnFoodName.setOnClickListener {
            getBeerByFoodName()
        }

        binding.btnBeerName.setOnClickListener {
            getBeerByName()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun getBeerByFoodName(){
        NetworkManager.getBeerListByFood(binding.etUser.text.toString()).enqueue(object:
            Callback<List<BeerListData>?>{
                override fun onResponse(
                    call: Call<List<BeerListData>?>,
                    response: Response<List<BeerListData>?>
                ) {
                    if(response.isSuccessful){
                        Log.d("Response",response.headers().toString())
                        val cl: String = response.body().toString()
                        Log.d("Response body",cl.toString())
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

    private fun getBeerByName(){
        NetworkManager.getBeerListByName(binding.etGame.text.toString()).enqueue(object:
            Callback<List<BeerListData>?>{
            override fun onResponse(
                call: Call<List<BeerListData>?>,
                response: Response<List<BeerListData>?>
            )
            {
                Log.d("Response header",response.headers().toString())
                if(response.isSuccessful){
                    Log.d("Response",response.body().toString())
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
}