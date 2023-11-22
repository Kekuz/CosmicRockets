package com.cosmicrockets.di

import android.content.Context
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.network.RetrofitNetworkClient
import com.cosmicrockets.data.repository.RocketRepositoryImpl
import com.cosmicrockets.domain.api.repository.RocketRepository
import dagger.Module
import dagger.Provides
@Module
class DataModule {

    @Provides
    fun provideNetworkClient(context: Context): NetworkClient {
        return RetrofitNetworkClient(context)
    }
    @Provides
    fun provideRocketRepository(networkClient: NetworkClient): RocketRepository {
        return RocketRepositoryImpl(networkClient)
    }
}