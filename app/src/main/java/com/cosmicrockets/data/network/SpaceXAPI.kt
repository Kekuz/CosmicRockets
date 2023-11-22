package com.cosmicrockets.data.network

import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPI {

    @POST("v4/rockets/query")
    fun searchRockets(): Call<RocketSearchResponse>
}