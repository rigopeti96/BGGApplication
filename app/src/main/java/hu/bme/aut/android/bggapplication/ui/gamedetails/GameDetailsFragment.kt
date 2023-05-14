package hu.bme.aut.android.bggapplication.ui.gamedetails

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.bggapplication.R
import hu.bme.aut.android.bggapplication.databinding.FragmentGameListBinding
import hu.bme.aut.android.bggapplication.databinding.GameDetailsFragmentBinding
import hu.bme.aut.android.bggapplication.network.NetworkManager
import hu.bme.aut.android.bggapplication.ui.gamelist.GameListViewModel
import hu.bme.aut.android.bggapplication.ui.main.MainFragment

class GameDetailsFragment : Fragment() {
    private lateinit var viewModel: GameDetailsViewModel
    private lateinit var binding: GameDetailsFragmentBinding
    private var gameId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[GameDetailsViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        NetworkManager.getGameDetailsData(gameId)
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}