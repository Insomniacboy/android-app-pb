package com.example.probooks.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.probooks.MainActivity
import com.example.probooks.R
import com.example.probooks.databinding.FragmentEventDetailBinding
import com.example.probooks.viewmodels.EventViewModel
import com.squareup.picasso.Picasso
import java.util.*

class EventDetailFragment : Fragment() {
    private lateinit var binding: FragmentEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(EventViewModel::class.java)}


    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        binding.progressLayout.visibility=View.VISIBLE


        val args = EventDetailFragmentArgs.fromBundle(requireArguments())
        val item = args.eventItem


        binding.titleTextView.text=item!!.title
        binding.placeTextView.text=item.author
        Picasso.get().load(item.image).into(binding.imageView)


        viewModel.fetchEvent(item.url).observe(viewLifecycleOwner, Observer {
            binding.detailTextView.text = it.detail
            //binding.titleTextView.text = it.title
            binding.progressLayout.visibility= View.GONE
        })

        return binding.root
    }


}
