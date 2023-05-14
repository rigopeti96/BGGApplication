package hu.bme.aut.android.bggapplication.ui.main

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log
import android.util.Xml
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hu.bme.aut.android.bggapplication.R
import hu.bme.aut.android.bggapplication.data.SearchListByName
import hu.bme.aut.android.bggapplication.databinding.MainFragmentBinding
import hu.bme.aut.android.bggapplication.network.BoardGameGeekAPI
import hu.bme.aut.android.bggapplication.network.NetworkManager
import okhttp3.ResponseBody

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
        binding.btnUser.setOnClickListener {
            getCollectionList()
        }

        binding.btnGame.setOnClickListener {
            getGameList()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }

    private fun getCollectionList(){
        NetworkManager.getCollectionList(binding.etUser.text.toString()).enqueue(object:
            Callback<ResponseBody?>{
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    if(response.isSuccessful){
                        Log.d("Response",response.body().toString())
                    } else {
                        Log.d("Response",response.body().toString())
                        Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    t.printStackTrace()
                    Toast.makeText(requireContext(), "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
                }

            }
        )
    }

    private fun getGameList(){
        NetworkManager.getGameList(binding.etGame.text.toString()).enqueue(object:
            Callback<ResponseBody?>{
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: Response<ResponseBody?>
            )
            {
                if(response.isSuccessful){
                    Log.d("Response",response.raw().toString())
                } else {
                    Log.d("Response",response.body().toString())
                    Toast.makeText(requireContext(), "Error: " + response.message(), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                t.printStackTrace()
                Toast.makeText(requireContext(), "Network request error occured, check LOG", Toast.LENGTH_LONG).show()
            }

        }
        )
    }
}