package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchOptions
import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchQuery
import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchRequestBody
import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchRocket
import com.cosmicrockets.data.network.dto.launch.launch_request_body.LaunchSort

object LaunchRequestBodyCreator {
    fun create(page:Int, rocketId: String): LaunchRequestBody{
        return LaunchRequestBody(
            LaunchQuery(LaunchRocket(rocketId)),
            LaunchOptions(
                MISSIONS_ON_PAGE,
                page,
                LaunchSort(SORT_TYPE)
            )
        )
    }

    private const val MISSIONS_ON_PAGE = 10
    private const val SORT_TYPE = "desc"

}