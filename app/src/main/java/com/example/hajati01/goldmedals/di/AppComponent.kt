package com.example.hajati01.quickdagger

import com.example.hajati01.goldmedals.application.App
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [(AndroidInjectionModule::class), (AppModule::class)])
interface AppComponent : AndroidInjector<App>

