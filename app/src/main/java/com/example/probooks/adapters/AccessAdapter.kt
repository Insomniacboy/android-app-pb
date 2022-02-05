package com.example.probooks.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.probooks.BR
import com.example.probooks.databinding.AccessEventItemBinding
import com.example.probooks.fragments.NotificationsFragmentDirections
import com.example.probooks.models.AccessItem
import com.squareup.picasso.Picasso

class AccessAdapter(private val context: Context): RecyclerView.Adapter<AccessAdapter.ViewHolder>(){

    private var items = mutableListOf<AccessItem>()
    fun setListData(data: MutableList<AccessItem>) {
        items = data
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccessAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AccessEventItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: AccessAdapter.ViewHolder, position: Int) = holder.bind(items[position])


    inner class ViewHolder(val binding: AccessEventItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AccessItem) = with(itemView){
            //this is two-way binding (BR - auto-generating class)
            binding.setVariable(BR.item, item)
            //applying changes
            binding.executePendingBindings()
            //setting image
            Picasso.get().load(item.accessimage).into(binding.imageView)

            itemView.setOnClickListener{
                //navigating to other fragment with Safe Args
                val action = NotificationsFragmentDirections.actionNavigationNotificationsToAccessEventDetailFragment(item)
                findNavController().navigate(action)
            }

        }
    }

}