package com.example.probooks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probooks.adapters.AccessAdapter
import com.example.probooks.databinding.FragmentNotificationsBinding
import com.example.probooks.viewmodels.AccessViewModel

class NotificationsFragment : Fragment() {

    private lateinit var binding: FragmentNotificationsBinding
    private val viewModel by lazy {ViewModelProvider(this).get(AccessViewModel::class.java)}
    private lateinit var adapter: AccessAdapter
    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        binding.progressBar.visibility = View.VISIBLE

        binding.recyclerView.layoutManager= LinearLayoutManager(requireActivity())
        adapter= AccessAdapter(requireActivity())
        binding.recyclerView.adapter=adapter

        //in fragment need set owner viewLifecycle Owner :::important
        viewModel.fetchAccessData().observe(viewLifecycleOwner, Observer {
            adapter.setListData(it)
            binding.progressBar.visibility = View.GONE
        })

        return binding.root
    }
}