package com.example.probooks.fragments

import android.R
import android.app.SearchManager
import android.content.Context
import android.os.Bundle
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


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {ViewModelProvider(this).get(EventViewModel::class.java)}
    private lateinit var adapter: EventAdapter
    private val eventItems: MutableList<EventItem> = mutableListOf()
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE
        binding.recyclerView.layoutManager=LinearLayoutManager(requireActivity())
        adapter= EventAdapter(requireActivity())
        binding.recyclerView.adapter=adapter


        //in fragment need set owner viewLifecycle Owner :::important
        viewModel.fetchData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            binding.progressBar.visibility = View.GONE
        })

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater){
        inflater.inflate(com.example.probooks.R.menu.toolbar_item, menu)
        val searchViewItem = menu!!.findItem(com.example.probooks.R.id.action_search)
        // Get the search view and set the searchable configuration
        val searchManager = activity?.getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchViewItem.actionView as SearchView
        adapter= EventAdapter(requireActivity())
        searchView.queryHint = "Что бы почитать..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(requireActivity().componentName))
        searchView.setIconifiedByDefault(false)  //Do not iconfy the widget; expand it by default

        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    val newText = newText.lowercase()
                    val newList: MutableList<EventItem> = mutableListOf()
                    for (item: EventItem in eventItems) {
                        val title: String = item.title.lowercase()
                        val author: String  = item.author.lowercase()

                        // you can specify as many conditions as you like
                        if (title.contains(newText) or author.contains(newText)) {
                            newList.add(item)
                        }
                    }
                    // create method in adapter
                    adapter.setFilter(newList)
                    return true
                }
            }
        searchView.setOnQueryTextListener(queryTextListener)
    }
}