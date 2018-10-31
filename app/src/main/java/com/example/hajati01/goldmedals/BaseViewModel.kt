package com.example.hajati01.goldmedals

import android.arch.lifecycle.ViewModel
import com.example.hajati01.goldmedals.viewmodel.MainViewModel


abstract class BaseViewModel:ViewModel(){
    private val injector: ViewModelInjector = DaggerViewModelInjector
            .builder()
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