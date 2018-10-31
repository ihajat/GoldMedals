package com.example.hajati01.goldmedals.viewmodel

import android.arch.lifecycle.LiveData
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.model.CountryDao
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MainViewModel( val countryDao: CountryDao): BaseViewModel() {
    val listCountry: LiveData<List<Country>>

    init {
        listCountry = countryDao.getAllCountries()
    }

    fun getListCountries(): LiveData<List<Country>> {
        return listCountry
    }

    fun deleteAllCountries() {
        countryDao.deleteAllCountries()
    }

    fun addCountry(country: Country) {
        launch(UI) {
            val query = async(CommonPool) { // Async stuff
                countryDao.insertCountry(country)
            }
            query.await()
        }
    }

    fun deleteCountry(country: Country) {
        launch(UI) {
            val query = async(CommonPool) { // Async stuff
                countryDao.deleteCountry(country)
            }
            query.await()
        }
    }

    fun updateCountry(country: Country) {
        launch(UI) {
            val query = async(CommonPool) { // Async stuff
                countryDao.updateCountry(country)
            }
            query.await()
        }
    }

    fun getCountryById(currentCountry: Int): Country {
        return countryDao.getCountryById(currentCountry)
    }
}
