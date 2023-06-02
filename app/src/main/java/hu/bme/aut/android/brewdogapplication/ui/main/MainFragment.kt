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
import hu.bme.aut.android.brewdogapplication.MainActivity
import hu.bme.aut.android.brewdogapplication.R
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
            (activity as MainActivity).getBeerByFoodName(binding.etFoodName.text.toString())
        }

        binding.btnBeerName.setOnClickListener {
            (activity as MainActivity).getBeerByName(binding.etBeerName.text.toString())
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}