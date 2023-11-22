package com.cosmicrockets.app

import android.app.Application
import com.cosmicrockets.di.AppComponent
import com.cosmicrockets.di.AppModule
import com.cosmicrockets.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

    }
}