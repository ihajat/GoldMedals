package com.example.hajati01.goldmedals.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.R
import com.example.hajati01.goldmedals.model.CountryDb
import com.example.hajati01.goldmedals.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CountryRecyclerAdapter.OnItemClickListener {

    private var countryRecyclerView: RecyclerView? = null
    private var recyclerViewAdapter: CountryRecyclerAdapter? = null

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private var db: CountryDb? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = CountryDb.getDataBase(this)

        countryRecyclerView = findViewById(R.id.recycler_view)
        recyclerViewAdapter = CountryRecyclerAdapter(arrayListOf(), this)

        countryRecyclerView!!.layoutManager = LinearLayoutManager(this)
        countryRecyclerView!!.adapter = recyclerViewAdapter

        viewModel.getListCountries().observe(this, Observer { countrys ->
            recyclerViewAdapter!!.addCountries(countrys!!)
        })
        fab.setOnClickListener {
            var intent = Intent(applicationContext, CountryDetailsActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_all_items -> {
                deleteAllCountrys()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteAllCountrys() {
        db!!.daoCountry().deleteAllCountries()
    }

    override fun onItemClick(country: Country) {
        var intent = Intent(applicationContext, CountryDetailsActivity::class.java)
        intent.putExtra("idCountry", country.id)
        startActivity(intent)
    }
}

