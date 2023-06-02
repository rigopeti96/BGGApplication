package hu.bme.aut.android.brewdogapplication.ui.beerdetails

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.brewdogapplication.MainActivity
import hu.bme.aut.android.brewdogapplication.databinding.BeerDetailsFragmentBinding
import hu.bme.aut.android.brewdogapplication.network.NetworkManager
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment

class BeerDetailsFragment : Fragment() {
    private lateinit var viewModel: BeerDetailsViewModel
    private lateinit var binding: BeerDetailsFragmentBinding
    private var gameId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BeerDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BeerDetailsViewModel::class.java]
        (activity as MainActivity).getBeerData(1)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkManager.getBeerById(gameId)
        binding.tvBackButton.setOnClickListener {
            (activity as MainActivity).changeToBeerList()
        }
    }

    companion object {
        fun newInstance() = MainFragment()
    }


}