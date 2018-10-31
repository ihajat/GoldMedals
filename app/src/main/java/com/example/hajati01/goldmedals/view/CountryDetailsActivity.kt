package com.example.hajati01.goldmedals.view

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.R
import com.example.hajati01.goldmedals.viewmodel.MainViewModel
import com.example.hajati01.goldmedals.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_country_details.*
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class CountryDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var country: Country

    private val currentCountry: Int by lazy {
        intent.getIntExtra("idCountry", -1)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_details)

        viewModel = ViewModelProviders.of(this, ViewModelFactory(this)).get(MainViewModel::class.java)

        if (currentCountry != -1) {
            setTitle(R.string.edit_country_title)
            getCountryById(currentCountry)
        } else {
            setTitle(R.string.add_country_title)
            invalidateOptionsMenu()
        }
    }

    private fun getCountryById(currentCountry: Int)  {
        launch(UI) {
            val query = async(CommonPool) { // Async stuff
                viewModel.getCountryById(currentCountry)
            }

            country= query.await()

            name_edit_text.setText(country.name)
            golds_edit_text.setText(country.golds.toString())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
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
        val nameCountry = name_edit_text.text.toString()
        val numberCountry = golds_edit_text.text.toString().toInt()
        val country = Country(0, nameCountry, numberCountry)
        viewModel.addCountry(country)
    }

    private fun deleteCountry() {
        viewModel.deleteCountry(country)
    }

    private fun updateCountry() {
        val nameCountry = name_edit_text.text.toString()
        val numberCountry = golds_edit_text.text.toString().toInt()
        val country = Country(country.id, nameCountry, numberCountry)
        viewModel.updateCountry(country)
    }
}
