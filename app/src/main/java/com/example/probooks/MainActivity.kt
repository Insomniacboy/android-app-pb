package com.example.probooks

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.probooks.databinding.ActivityMainBinding
import com.example.probooks.viewmodels.AccessViewModel
import com.example.probooks.viewmodels.EventViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
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
    fun partner1(view: View){
        val url = "https://www.bankasia.kg/ru/"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }
    fun partner2(view: View){
        val url = "https://bakertilly-ca.com"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun ProKG(view: View){
        val url = "http://prokg.org/ru"
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    fun MailRed(view: View){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:prokgclub@gmail.com") // only email apps should handle this
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun getSelectedItem(bottomNavigationView: BottomNavigationView): Int {
        val menu = bottomNavigationView.menu
        for (i in 0 until bottomNavigationView.menu.size()) {
            val menuItem: MenuItem = menu.getItem(i)
            if (menuItem.isChecked()) {
                return menuItem.getItemId()
            }
        }
        return 0
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

        nav_view.setOnItemReselectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    navController.popBackStack()
                    true
                }
                R.id.navigation_notifications -> {
                        navController.popBackStack()
                        true
                }
                else -> false
            }
        }

        viewModel.init(this)
        viewModel.fetchData().observe(this, Observer{
            Log.d("fetchData", "$it")
        })
        viewAccessModel.init(this)
        viewAccessModel.fetchAccessData().observe(this, Observer{
            Log.d("fetchAccessData", "$it")
        })
    }
    override fun onSupportNavigateUp(): Boolean {
        //Handle the toolbar back button here.
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        navController.currentDestination?.let {
            if (it.id == R.id.navigation_home) {
            } else {
                navController.popBackStack()
            }
        }
        return super.onSupportNavigateUp()
    }

}
