package com.example.hajati01.goldmedals.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.viewmodel.MainViewModel
import com.example.hajati01.goldmedals.R
import com.example.hajati01.goldmedals.model.CountryDao
import com.example.hajati01.goldmedals.model.CountryDb
import kotlinx.android.synthetic.main.activity_country_details.*


class CountryDetailsActivity : AppCompatActivity() {

    private var countryDao: CountryDao? = null
    private var viewModel: MainViewModel? = null

    private var currentCountry: Int? = null
    private var country: Country? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)
        var db: CountryDb = CountryDb.getDataBase(this)

        countryDao = db.daoCountry()

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        currentCountry = intent.getIntExtra("idCountry", -1)
        if (currentCountry != -1) {
            setTitle(R.string.edit_country_title)
            country = countryDao!!.getCountryById(currentCountry!!)
            name_edit_text.setText(country!!.name)
            golds_edit_text.setText(country!!.golds.toString())
        } else {
            setTitle(R.string.add_country_title)
            invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        var inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_items, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.done_item -> {
                if (currentCountry == -1) {
                    saveCountry()
                    Toast.makeText(this, getString(R.string.save_country), Toast.LENGTH_SHORT).show()
                } else {
                    updateCountry()
                    Toast.makeText(this, getString(R.string.update_country), Toast.LENGTH_SHORT).show()
                }

                finish()
            }
            R.id.delete_item -> {
                deleteCountry()
                Toast.makeText(this, getString(R.string.delete_country), Toast.LENGTH_SHORT).show()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPrepareOptionsMenu(menu: Menu): Boolean {
        super.onPrepareOptionsMenu(menu)
        if (currentCountry == -1) {
            menu.findItem(R.id.delete_item).isVisible = false
        }
        return true
    }

    private fun saveCountry() {
        var nameCountry = name_edit_text.text.toString()
        var numberCountry = golds_edit_text.text.toString().toInt()
        var country = Country(0, nameCountry, numberCountry)
        viewModel!!.addCountry(country)
    }

    private fun deleteCountry() {
        countryDao!!.deleteCountry(country!!)
    }

    private fun updateCountry() {
        var nameCountry = name_edit_text.text.toString()
        var numberCountry = golds_edit_text.text.toString().toInt()
        var country = Country(country!!.id, nameCountry, numberCountry)
        countryDao!!.updateCountry(country)
    }
}
