package com.example.probooks.fragments

import android.R
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probooks.adapters.EventAdapter
import com.example.probooks.databinding.FragmentHomeBinding
import com.example.probooks.models.EventItem
import com.example.probooks.viewmodels.EventViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {ViewModelProvider(this).get(EventViewModel::class.java)}
    private lateinit var adapter: EventAdapter
    private val eventItems: MutableList<EventItem> = mutableListOf()
    private val newList: MutableList<EventItem> = mutableListOf()
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        binding.error.visibility=View.GONE
        binding.recyclerView.layoutManager=LinearLayoutManager(requireActivity())
        adapter= EventAdapter(requireActivity())
        binding.recyclerView.adapter=adapter
        eventItems.clear()

        //in fragment need set owner viewLifecycle Owner :::important
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            eventItems.addAll(it)
            if (eventItems.isEmpty() ) {
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
            searchView.queryHint = "Что бы почитать..."
            searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newList.clear()
                    if (newText!!.isNotEmpty()) {
                        val search = newText.lowercase(Locale.getDefault())
                        eventItems.forEach {
                            // you can specify as many conditions as you like
                            if (it.title.lowercase(Locale.getDefault()).contains(search) or it.author.lowercase(Locale.getDefault()).contains(search)) {
                                if (!newList.contains(it)) {
                                    newList.add(it)
                                }
                            }
                        }
                        adapter.setListData(newList)
                    } else {
                        adapter.setListData(eventItems)
                    }
                    Log.d("FilterList", newList.toString())
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