package com.cosmicrockets.di

import com.cosmicrockets.ui.main.MainActivity
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}