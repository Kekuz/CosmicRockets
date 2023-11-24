package com.cosmicrockets.data.network

import com.cosmicrockets.data.network.dto.launch.LaunchSearchResponse
import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchRequestBody
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPI {

    @POST("v4/rockets/query")
    fun searchRockets(): Call<RocketSearchResponse>

    @POST("v4/launches/query")
    fun searchLaunches(
        @Body body: LaunchRequestBody
    ): Call<LaunchSearchResponse>
}