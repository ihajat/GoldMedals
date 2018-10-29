package com.example.hajati01.goldmedals.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.os.AsyncTask
import com.example.hajati01.goldmedals.Country
import com.example.hajati01.goldmedals.model.CountryDb


class MainViewModel(application: Application) : AndroidViewModel(application) {

    var listCountry: LiveData<List<Country>>
    private val appDb: CountryDb

    init {
        appDb = CountryDb.getDataBase(this.getApplication())
        listCountry = appDb.daoCountry().getAllCountries()
    }

    fun getListCountries(): LiveData<List<Country>> {
        return listCountry
    }

    fun addCountry(country: Country) {
        addAsynTask(appDb).execute(country)
    }


    class addAsynTask(db: CountryDb) : AsyncTask<Country, Void, Void>() {
        private var contactDb = db
        override fun doInBackground(vararg params: Country): Void? {
            contactDb.daoCountry().insertCountry(params[0])
            return null
        }

    }

}