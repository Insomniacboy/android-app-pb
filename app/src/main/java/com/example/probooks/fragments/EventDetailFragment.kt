package com.example.probooks.fragments

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.example.probooks.R
import com.example.probooks.databinding.FragmentEventDetailBinding
import com.example.probooks.repository.Response
import com.example.probooks.viewmodels.EventViewModel
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_event_detail.*
import java.net.URL
import java.util.*
import java.util.concurrent.Executors


class EventDetailFragment : Fragment() {
    private lateinit var binding: FragmentEventDetailBinding
    private val viewModel by lazy { ViewModelProvider(this).get(EventViewModel::class.java)}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Executors.newSingleThreadExecutor().execute {
            try {
                val btn = binding.orderView
                var response = URL("https://probooks.space/YxAjdSjWv8aOpJX2LlfqF3VE3x4=").readText()
                var gson = Gson()
                val post: Response = gson.fromJson(response, Response::class.java)
                val number = post.whatsapp_number
                btn.setOnClickListener {
                    try {
                        val pm: PackageManager = btn.context.packageManager
                        val title2 = titleTextView.text
                        val author = placeTextView.text
                        pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
                        val url =
                            "https://api.whatsapp.com/send?phone=$number&text=Здравствуйте! Я хочу забронировать книгу: \"$title2\" от автора: $author";
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse(url)
                        startActivity(intent)
                    } catch (e: PackageManager.NameNotFoundException)
                    {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "+$number"));
                        startActivity(intent)
                    }
                }
            } catch (ex: Exception) {
                Log.d("Error", "$ex")
            }
        }
    }


}
