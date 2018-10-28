package com.example.hajati01.quickdagger

import com.example.hajati01.goldmedals.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AppModule {
    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}