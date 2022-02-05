package com.example.probooks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.probooks.databinding.FragmentAccessEventDetailBinding
import com.example.probooks.viewmodels.AccessViewModel
import com.squareup.picasso.Picasso

class AccessEventDetailFragment : Fragment() {
    private lateinit var binding: FragmentAccessEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(AccessViewModel::class.java)}


    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccessEventDetailBinding.inflate(inflater, container, false)

        binding.progressLayout.visibility= View.VISIBLE


        val args = AccessEventDetailFragmentArgs.fromBundle(requireArguments())
        val item = args.accessItem


        binding.titleTextView.text=item!!.accesstitle
        binding.placeTextView.text=item.accessauthor
        Picasso.get().load(item.accessimage).into(binding.imageView)


        viewModel.fetchAccessEvent(item.access_url).observe(viewLifecycleOwner, Observer {
            binding.detailTextView.text = it.detail
            //binding.titleTextView.text = it.title
            binding.progressLayout.visibility= View.GONE
        })

        return binding.root
    }


}