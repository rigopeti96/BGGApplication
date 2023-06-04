package hu.bme.aut.android.brewdogapplication.adapter

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.navigation.fragment.findNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import hu.bme.aut.android.brewdogapplication.MainActivity
import hu.bme.aut.android.brewdogapplication.R
import hu.bme.aut.android.brewdogapplication.data.BeerListData
import hu.bme.aut.android.brewdogapplication.databinding.ItemBeerListBinding
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsFragment
import hu.bme.aut.android.brewdogapplication.ui.beerdetails.BeerDetailsViewModel
import hu.bme.aut.android.brewdogapplication.ui.main.MainFragment
import hu.bme.aut.android.brewdogapplication.ui.main.MainViewModel

class BeerListAdapter(private val activity: MainActivity, private val beerDetailsViewModel: BeerDetailsViewModel): RecyclerView.Adapter<BeerListAdapter.ReportItemViewHolder>() {
    private val items = mutableListOf<BeerListData>()
    private lateinit var binding: ItemBeerListBinding
    private lateinit var firebaseAnalytics: FirebaseAnalytics

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportItemViewHolder {
        binding = ItemBeerListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        firebaseAnalytics = Firebase.analytics
        return ReportItemViewHolder(binding.root)
    }

    inner class ReportItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val tvBeerListTagline: TextView = binding.tvBeerListTagline
        val tvBeerListName: TextView = binding.tvBeerListName
        val tvBeerListAbv: TextView = binding.tvBeerListAbv
        val clBeerLayout: ConstraintLayout = binding.clBeerLayout

        var item: BeerListData? = null
    }

    override fun getItemCount() = items.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ReportItemViewHolder, position: Int) {
        val item = items[position]

        holder.tvBeerListTagline.text = item.tagline
        holder.tvBeerListName.text = item.name
        holder.tvBeerListAbv.text = "${item.abv} %"
        holder.item = item

        holder.clBeerLayout.setOnClickListener {
            transportToDatasheet(item.id, item.name)
        }
    }

    private fun transportToDatasheet(itemId: Int, itemName: String){
        beerDetailsViewModel.setBeerId(itemId)
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT){
            param(FirebaseAnalytics.Param.ITEM_ID, itemId.toString())
            param(FirebaseAnalytics.Param.ITEM_NAME, itemName)
        }

        activity.changeToBeerDatasheet()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addBeerItem(beerListItem: BeerListData){
        items.add(beerListItem)
        Log.d("Add beer item", "called")
        notifyDataSetChanged()
    }
}