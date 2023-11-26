package com.cosmicrockets.di

import android.content.Context
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.SettingsStorage
import com.cosmicrockets.data.network.RetrofitNetworkClient
import com.cosmicrockets.data.repository.LaunchRepositoryImpl
import com.cosmicrockets.data.repository.RocketRepositoryImpl
import com.cosmicrockets.data.repository.SettingsRepositoryImpl
import com.cosmicrockets.data.storage.SharedPrefsSettingsStorage
import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.api.repository.SettingsRepository
import dagger.Module
import dagger.Provides
@Module
class DataModule {

    @Provides
    fun provideNetworkClient(context: Context): NetworkClient {
        return RetrofitNetworkClient(context)
    }

    @Provides
    fun provideSettingsStorage(context: Context): SettingsStorage{
        return SharedPrefsSettingsStorage(context)
    }

    @Provides
    fun provideRocketRepository(networkClient: NetworkClient): RocketRepository {
        return RocketRepositoryImpl(networkClient)
    }

    @Provides
    fun provideLaunchRepository(networkClient: NetworkClient): LaunchRepository {
        return LaunchRepositoryImpl(networkClient)
    }

    @Provides
    fun provideSettingsRepository(settingsStorage: SettingsStorage): SettingsRepository {
        return SettingsRepositoryImpl(settingsStorage)
    }
}