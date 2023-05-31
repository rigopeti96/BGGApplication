package hu.bme.aut.android.brewdogapplication.ui.beerlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.brewdogapplication.databinding.FragmentBeerListBinding


class BeerListFragment : Fragment() {
    private lateinit var viewModel: BeerListViewModel
    private lateinit var binding: FragmentBeerListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBeerListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BeerListViewModel::class.java]
    }

    companion object {
        fun newInstance() = BeerListFragment()
    }
}