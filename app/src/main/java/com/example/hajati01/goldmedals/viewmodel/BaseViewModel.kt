package com.example.hajati01.goldmedals.viewmodel

import android.arch.lifecycle.ViewModel
import com.example.hajati01.goldmedals.di.DaggerViewModelInjector
import com.example.hajati01.goldmedals.di.ViewModelInjector


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
            .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainViewModel -> injector.inject(this)
        }
    }
}