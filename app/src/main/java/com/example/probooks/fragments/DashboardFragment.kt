package com.example.probooks.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.probooks.R
import com.example.probooks.databinding.FragmentDashboardBinding
import com.example.probooks.viewmodels.DashboardViewModel
import com.example.probooks.viewmodels.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {
    private lateinit var binding: FragmentDashboardBinding
    private lateinit var dashboardViewModel: DashboardViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root: View = inflater.inflate(R.layout.fragment_dashboard, container, false)

        binding = FragmentDashboardBinding.inflate(inflater, container, false)

        val imageUrl1="https://probooks.space/custom-img/bank-asia.png"
        val imageUrl2="https://probooks.space/custom-img/baker-tilly.png"

        Picasso.get().load(imageUrl1).into(binding.partner1)
        Picasso.get().load(imageUrl2).into(binding.partner2)

        return binding.root
    }
}