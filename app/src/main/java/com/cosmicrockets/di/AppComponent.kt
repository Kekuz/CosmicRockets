package com.cosmicrockets.di

import com.cosmicrockets.ui.launches.LaunchesFragment
import com.cosmicrockets.ui.viewpager.RocketsVPFragment
import dagger.Component

@Component(modules = [AppModule::class, DomainModule::class, DataModule::class])
interface AppComponent {

    fun inject(rocketsVPFragment: RocketsVPFragment)

    fun inject(launchesFragment: LaunchesFragment)
}