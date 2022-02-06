package com.example.probooks

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.probooks.adapters.EventAdapter
import com.example.probooks.databinding.ActivityMainBinding
import com.example.probooks.fragments.DashboardFragment
import com.example.probooks.models.EventItem
import com.example.probooks.viewmodels.AccessViewModel
import com.example.probooks.viewmodels.EventViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_event_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {ViewModelProvider(this).get(EventViewModel::class.java)}
    private val viewAccessModel by lazy {ViewModelProvider(this).get(AccessViewModel::class.java)}


    fun OnClick(view: View){
        val url = "https://probooks.space/wishlist/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun redirectToOrder(view: View){
        try {
            val pm: PackageManager = view.getContext().getPackageManager()
            pm.getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES)
            val url = "https://api.whatsapp.com/send?phone=" + "996504368000" + "&text=" + "Здравствуйте! Я хочу забронировать книгу: " + "\"" + titleTextView.text + "\"" + " от автора: " + placeTextView.text;
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        } catch (e: PackageManager.NameNotFoundException)
        {
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "+996504368000"));
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val navController = findNavController(R.id.nav_host_fragment_activity_main)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        val adapter = EventAdapter(this)

        books_search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })

        Log.d("ADebugTag", "Value: " + adapter.itemsFilterList);

        viewModel.init(this)
        viewModel.fetchData().observe(this, Observer{
            Log.d("fetchData", "$it")
        })
        viewAccessModel.init(this)
        viewAccessModel.fetchAccessData().observe(this, Observer{
            Log.d("fetchAccessData", "$it")
        })
    }

}
