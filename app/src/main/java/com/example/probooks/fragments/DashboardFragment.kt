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

class DashboardFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val root: View = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }
}