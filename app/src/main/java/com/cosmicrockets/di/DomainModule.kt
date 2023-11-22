package com.cosmicrockets.di

import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.impl.SearchRocketsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideSearchRocketUseCase(rocketRepository: RocketRepository): SearchRocketsUseCase {
        return SearchRocketsUseCaseImpl(rocketRepository)
    }
}