package hu.bme.aut.android.bggapplication.ui.gamelist

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import hu.bme.aut.android.bggapplication.R
import hu.bme.aut.android.bggapplication.databinding.GameDetailsFragmentBinding
import hu.bme.aut.android.bggapplication.ui.main.MainFragment
import hu.bme.aut.android.bggapplication.ui.main.MainViewModel


class GameListFragment : Fragment() {
    private lateinit var viewModel: GameListViewModel
    private lateinit var binding: GameDetailsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GameDetailsFragmentBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = ViewModelProvider(this)[GameListViewModel::class.java]
    }

    companion object {
        fun newInstance() = GameListFragment()
    }
}