package com.example.hajati01.goldmedals.di

import com.example.hajati01.goldmedals.viewmodel.MainViewModel
import dagger.Component

import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [])
interface ViewModelInjector {

    /**
     * Injects required dependencies into the specified PostViewModel.
     * @param postViewModel PostViewModel in which to inject the dependencies
     */
    fun inject(mainViewModel: MainViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

    }
}