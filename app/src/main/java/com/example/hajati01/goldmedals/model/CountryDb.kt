package com.example.hajati01.goldmedals.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.example.hajati01.goldmedals.Country
import java.util.concurrent.Executors

@Database(entities = arrayOf(Country::class, DataEntity::class), version = 2)
abstract class CountryDb : RoomDatabase() {

    abstract fun daoCountry(): CountryDao

    abstract fun daoData(): DataDao


    companion object {

        private var INSTANCE: CountryDb? = null

        @Synchronized
        fun getInstance(context: Context): CountryDb {
            if (INSTANCE == null) {
                INSTANCE = buildDatabase(context)
            }
            return INSTANCE as CountryDb
        }

        private fun buildDatabase(context: Context): CountryDb {
            return Room.databaseBuilder(context,
                    CountryDb::class.java,
                    "goldmedals-db")
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Executors.newSingleThreadScheduledExecutor().execute { getInstance(context).daoData().insertAll(*DataEntity.populateData(context)) }
                        }
                    })
                    .build()
        }
    }
}