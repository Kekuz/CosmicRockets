package com.cosmicrockets.data.repository

import android.util.Log
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.mapper.LaunchMapper
import com.cosmicrockets.data.mapper.LaunchRequestBodyCreator
import com.cosmicrockets.data.network.dto.launch.LaunchSearchRequest
import com.cosmicrockets.data.network.dto.launch.LaunchSearchResponse
import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.models.launch.LaunchResponse
import com.cosmicrockets.domain.util.Resource

class LaunchRepositoryImpl(private val networkClient: NetworkClient) : LaunchRepository {


    override fun search(page: Int, rocketId: String): Resource<LaunchResponse> {
        val response = networkClient.doRequest(
            LaunchSearchRequest(
                LaunchRequestBodyCreator.create(
                    page,
                    rocketId,
                    )
            )
        )
        Log.d("response", response.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Проверьте соединение с интернетом")
            }

            200 -> {
                Resource.Success(
                    LaunchResponse(
                        (response as LaunchSearchResponse).docs.map {
                            LaunchMapper.map(it)
                        },
                        response.page,
                        response.hasNextPage
                    )
                )
            }

            else -> Resource.Error("Ошибка сервера")
        }
    }

}