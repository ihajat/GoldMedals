package com.example.hajati01.goldmedals.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v7.app.AppCompatActivity
import com.example.hajati01.goldmedals.model.CountryDb


class ViewModelFactory(private val activity: AppCompatActivity): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val db = CountryDb.getDataBase(activity.applicationContext)
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(db.daoCountry()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}