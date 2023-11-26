package com.cosmicrockets.data.repository

import android.util.Log
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.mapper.RocketMapper
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.domain.util.Resource

class RocketRepositoryImpl(private val networkClient: NetworkClient) : RocketRepository {
    override fun search(): Resource<List<Rocket>> {
        val response = networkClient.doRequest("rocket_request")
        //Log.e("response result:", response.resultCode.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте соединение с интернетом")
            }

            200 -> {
                Log.d("Response",(response as RocketSearchResponse).docs.toString() )
                return Resource.Success((response as RocketSearchResponse).docs.map {
                    RocketMapper.map(it)
                })
            }

            else -> Resource.Error("Ошибка сервера")
        }
    }
}