package com.example.probooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.probooks.BR
import com.example.probooks.databinding.EventItemBinding
import com.example.probooks.fragments.HomeFragmentDirections
import com.example.probooks.models.EventItem
import com.squareup.picasso.Picasso
import java.util.*

class EventAdapter(private val context: Context): RecyclerView.Adapter<EventAdapter.ViewHolder>(){

    private var items = mutableListOf<EventItem>()
    private var itemsFilterList = mutableListOf<EventItem>()
    fun setListData(data: MutableList<EventItem>) {
        items = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = EventItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(items[position])

    inner class ViewHolder(val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: EventItem) = with(itemView) {
            //this is two-way binding (BR - auto-generating class)
            binding.setVariable(BR.item, item)
            //applying changes
            binding.executePendingBindings()
            //setting image
            Picasso.get().load(item.image).into(binding.imageView)

            itemView.setOnClickListener {
                //navigating to other fragment with Safe Args
                val action = HomeFragmentDirections.actionNavigationHomeToEventDetailFragment(item)
                findNavController().navigate(action)
            }
        }
    }

    fun setFilter(newList: MutableList<EventItem>) {
  //      items = newList
        items = mutableListOf()
        items.addAll(newList)
        notifyDataSetChanged()
    }

}