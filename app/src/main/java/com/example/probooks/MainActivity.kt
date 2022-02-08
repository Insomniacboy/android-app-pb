package com.example.probooks

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast
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
import com.example.probooks.models.EventItem
import com.example.probooks.viewmodels.AccessViewModel
import com.example.probooks.viewmodels.EventViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_event_detail.*
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by lazy {ViewModelProvider(this).get(EventViewModel::class.java)}
    private val viewAccessModel by lazy {ViewModelProvider(this).get(AccessViewModel::class.java)}
    private lateinit var adapter: EventAdapter
    private val adapter1: EventAdapter ?=null
    private val eventItems: MutableList<EventItem> = mutableListOf<EventItem>()

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

    fun MailRed(view: View){
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:prokgclub@gmail.com") // only email apps should handle this
        if (intent.resolveActivity(packageManager) != null) {
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

        viewModel.init(this)
        viewModel.fetchData().observe(this, Observer{
            Log.d("fetchData", "$it")
        })
        viewAccessModel.init(this)
        viewAccessModel.fetchAccessData().observe(this, Observer{
            Log.d("fetchAccessData", "$it")
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_item, menu);

        val searchViewItem = menu!!.findItem(R.id.action_search)
        // Get the search view and set the searchable configuration
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = searchViewItem.actionView as SearchView
        searchView.queryHint = "Search..."
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)

        val queryTextListener: SearchView.OnQueryTextListener =
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(s: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    var newText = newText
                    newText = newText.lowercase()
                    val newList: MutableList<EventItem> = mutableListOf()
                    for (item in eventItems) {
                        val title: String = item.title.lowercase()

                        // you can specify as many conditions as you like
                        if (title.contains(newText)) {
                            newList.add(item)
                        }
                    }
                    // create method in adapter
                    adapter1?.setFilter(newList)
                    return true
                }
            }

        searchView.setOnQueryTextListener(queryTextListener)

        return true

    }

}
