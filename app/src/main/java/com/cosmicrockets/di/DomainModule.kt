package com.cosmicrockets.di

import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.interactor.SettingsSavingInteractor
import com.cosmicrockets.domain.api.repository.DatabaseRepository
import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.api.repository.SettingsRepository
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.impl.interactor.DatabaseInteractorImpl
import com.cosmicrockets.domain.impl.interactor.SettingsSavingInteractorImpl
import com.cosmicrockets.domain.impl.usecase.SearchLaunchByIdUseCaseImpl
import com.cosmicrockets.domain.impl.usecase.SearchRocketsUseCaseImpl
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

    @Provides
    fun provideSettingsSavingInteractor(settingsRepository: SettingsRepository): SettingsSavingInteractor {
        return SettingsSavingInteractorImpl(settingsRepository)
    }

    @Provides
    fun provideDatabaseInteractor(databaseRepository: DatabaseRepository): DatabaseInteractor {
        return DatabaseInteractorImpl(databaseRepository)
    }
}