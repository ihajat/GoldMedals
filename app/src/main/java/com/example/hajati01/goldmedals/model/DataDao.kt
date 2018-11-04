package com.example.hajati01.goldmedals.model

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface DataDao {

    @get:Query("SELECT * FROM DataEntity")
    val all: List<DataEntity>

    @Insert
    fun insertAll(vararg dataEntities: DataEntity)


    @Query("select flag from DataEntity where name in (:id)")
    fun getFlagByCountry(id: String): String
}