package com.example.hajati01.goldmedals.model

import javax.inject.Inject

class MainViewModel {
    var test: String?=null

    @Inject
    constructor()
    {
        test = "fred"
    }
}