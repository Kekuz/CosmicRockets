package com.cosmicrockets.data.repository

import android.util.Log
import com.cosmicrockets.data.NetworkClient
import com.cosmicrockets.data.mapper.RocketMapper
import com.cosmicrockets.data.network.dto.rocket.PayloadWeightDto
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.models.rocket.Diameter
import com.cosmicrockets.domain.models.rocket.Height
import com.cosmicrockets.domain.models.rocket.Mass
import com.cosmicrockets.domain.models.rocket.PayloadWeight
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.domain.util.Resource

class RocketRepositoryImpl(private val networkClient: NetworkClient) : RocketRepository {
    override fun search(): Resource<List<Rocket>> {
        val response = networkClient.doRequest("rocket_request")
        //Log.e("response result:", response.resultCode.toString())
        return when (response.resultCode) {
            -1 -> {
                Resource.Error("Check internet connection")
            }

            200 -> {
                Log.e("Response",(response as RocketSearchResponse).docs.toString() )
                return Resource.Success((response as RocketSearchResponse).docs.map {
                    RocketMapper.map(it)
                })
            }

            else -> Resource.Error("Server error")
        }
    }
}