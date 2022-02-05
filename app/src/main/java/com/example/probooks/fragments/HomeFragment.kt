package com.example.probooks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probooks.adapters.EventAdapter
import com.example.probooks.databinding.FragmentHomeBinding
import com.example.probooks.viewmodels.EventViewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel by lazy {ViewModelProvider(this).get(EventViewModel::class.java)}
    private lateinit var adapter: EventAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
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

        return binding.root
    }

}