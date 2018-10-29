package com.example.hajati01.goldmedals.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.example.hajati01.goldmedals.Country


@Dao
interface CountryDao {
    @Query("select * from countries ORDER BY golds DESC")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("select * from countries where idCountry in (:id)")
    fun getCountryById(id: Int): Country

    @Query("delete from countries")
    fun deleteAllCountries()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCountry(country: Country)

    @Update
    fun updateCountry(country: Country)

    @Delete
    fun deleteCountry(country: Country)
}