package com.cosmicrockets.di

import android.content.Context
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.presentation.rockets_viewpager.RocketsVPFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideRocketsVPFactory(searchRocketsUseCase: SearchRocketsUseCase): RocketsVPFactory {
        return RocketsVPFactory(searchRocketsUseCase)
    }
}