package com.example.probooks.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.probooks.databinding.FragmentEventDetailBinding
import com.example.probooks.viewmodels.EventViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_event_detail.*
import java.util.*

class EventDetailFragment : Fragment() {
    private lateinit var binding: FragmentEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(EventViewModel::class.java)}


    public override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentEventDetailBinding.inflate(inflater, container, false)

        binding.progressLayout.visibility=View.VISIBLE


        val args = EventDetailFragmentArgs.fromBundle(requireArguments())
        val item = args.eventItem
        val btn = binding.orderView
        btn.setOnClickListener {
            try {
                val pm: PackageManager = btn.getContext().getPackageManager()
                val title2 = titleTextView.text
                val author = placeTextView.text
                pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                val url =
                    "https://api.whatsapp.com/send?phone=996504368000&text=Здравствуйте! Я хочу забронировать книгу: \"$title2\" от автора: $author";
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)
            } catch (e: PackageManager.NameNotFoundException)
            {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+996504368000"));
                startActivity(intent)
            }
        }

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
