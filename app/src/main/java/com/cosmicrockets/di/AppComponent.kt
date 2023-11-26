package com.cosmicrockets.di

import android.app.Application
import com.cosmicrockets.ui.launches.LaunchesFragment
import com.cosmicrockets.ui.rocket.RocketFragment
import com.cosmicrockets.ui.viewpager.RocketsVPFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(rocketsVPFragment: RocketsVPFragment)

    fun inject(launchesFragment: LaunchesFragment)

    fun inject(rocketFragment: RocketFragment)
}