package hu.bme.aut.android.brewdogapplication.ui.beerlist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.brewdogapplication.R
import hu.bme.aut.android.brewdogapplication.adapter.BeerListAdapter
import hu.bme.aut.android.brewdogapplication.databinding.BeerListFragmentBinding


class BeerListFragment : Fragment() {
    private lateinit var viewModel: BeerListViewModel
    private lateinit var binding: BeerListFragmentBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BeerListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BeerListFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[BeerListViewModel::class.java]
        initRecycleView()
    }

    companion object {
        fun newInstance() = BeerListFragment()
    }

    private fun initRecycleView(){
        recyclerView = requireView().findViewById(R.id.rvBeerRecyclerView)
        adapter = BeerListAdapter(childFragmentManager.findFragmentById(R.id.fragment_beer_list) as BeerListFragment)
        recyclerView.layoutManager = LinearLayoutManager(requireContext()).apply {
            reverseLayout = true
            stackFromEnd = true
        }
        recyclerView.adapter = adapter
    }
}