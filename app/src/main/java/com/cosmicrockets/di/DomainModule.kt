package com.cosmicrockets.di

import android.util.Log
import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.impl.SearchLaunchByIdUseCaseImpl
import com.cosmicrockets.domain.impl.SearchRocketsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideSearchRocketUseCase(rocketRepository: RocketRepository): SearchRocketsUseCase {
        return SearchRocketsUseCaseImpl(rocketRepository)
    }

    @Provides
    fun provideSearchLaunchByIdUseCase(launchRepository: LaunchRepository): SearchLaunchByIdUseCase{
        return SearchLaunchByIdUseCaseImpl(launchRepository)
    }
}