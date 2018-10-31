package com.example.hajati01.goldmedals.model

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.hajati01.goldmedals.Country

@Database(entities = [(Country::class)], version = 2, exportSchema = false)
abstract class CountryDb : RoomDatabase() {
    companion object {
        private var INSTANCE: CountryDb? = null
        fun getDataBase(context: Context): CountryDb {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext, CountryDb::class.java, "goldmedals-db")
                       // .allowMainThreadQueries()
                        .build()
            }
            return INSTANCE as CountryDb
        }
    }

    abstract fun daoCountry(): CountryDao
}