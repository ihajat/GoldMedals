package com.example.hajati01.goldmedals

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "countries")
data class Country(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idCountry")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "golds")
    var golds: Int = 0,

    @ColumnInfo(name = "silvers")
    var silvers: Int = 0,

    @ColumnInfo(name = "bronzes")
    var bronzes: Int = 0,

    @ColumnInfo(name = "flag")
    var flag: String = ""
)