package com.example.probooks.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probooks.adapters.AccessAdapter
import com.example.probooks.databinding.FragmentNotificationsBinding
import com.example.probooks.models.AccessItem
import com.example.probooks.models.EventItem
import com.example.probooks.viewmodels.AccessViewModel
import java.util.*

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel by lazy {ViewModelProvider(this).get(AccessViewModel::class.java)}
    private lateinit var adapter: AccessAdapter
    private val accessItems: MutableList<AccessItem> = mutableListOf()
    private val newList: MutableList<AccessItem> = mutableListOf()
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        binding.error.visibility=View.GONE
        binding.recyclerView.layoutManager= LinearLayoutManager(requireActivity())
        adapter= AccessAdapter(requireActivity())
        binding.recyclerView.adapter=adapter
        accessItems.clear()

        //in fragment need set owner viewLifecycle Owner :::important
        viewModel.fetchAccessData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            accessItems.addAll(it)
            if (accessItems.isEmpty() ) {
                binding.error.visibility=View.VISIBLE
            }
            binding.progressBar.visibility = View.GONE
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(com.example.probooks.R.menu.toolbar_item, menu)
        val searchViewItem = menu.findItem(com.example.probooks.R.id.action_search)

        if (searchViewItem != null) {

            val searchView = searchViewItem.actionView as SearchView
            searchView.queryHint = "Книга на будущее..."
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newList.clear()
                    if (newText!!.isNotEmpty()) {
                        val search = newText.lowercase(Locale.getDefault())
                        accessItems.forEach {
                            // you can specify as many conditions as you like
                            if (it.accesstitle.lowercase(Locale.getDefault()).contains(search) or it.accessauthor.lowercase(Locale.getDefault()).contains(search)) {
                                if (!newList.contains(it)) {
                                    newList.add(it)
                                }
                            }
                        }
                        adapter.setListData(newList)
                    } else {
                        adapter.setListData(accessItems)
                    }
                    // create method in adapter
                    return true
                }
            })
        }
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }
}