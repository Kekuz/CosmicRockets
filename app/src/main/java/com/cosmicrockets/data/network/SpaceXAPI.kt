package com.cosmicrockets.data.network

import com.cosmicrockets.data.network.dto.launch.LaunchSearchResponse
import com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id.LaunchRequestBodyForSearchById
import com.cosmicrockets.data.network.dto.launch.request_body.search_last.LaunchRequestBodyForSearchLast
import com.cosmicrockets.data.network.dto.rocket.RocketSearchResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SpaceXAPI {

    @POST("v4/rockets/query")
    fun searchRockets(): Call<RocketSearchResponse>

    @POST("v4/launches/query")
    fun searchLaunchesById(
        @Body body: LaunchRequestBodyForSearchById
    ): Call<LaunchSearchResponse>

    @POST("v4/launches/query")
    fun searchLastLaunches(
        @Body body: LaunchRequestBodyForSearchLast
    ): Call<LaunchSearchResponse>
}