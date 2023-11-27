package com.cosmicrockets.data.mapper

import android.util.Log
import com.cosmicrockets.data.network.dto.launch.request_body.LaunchOptions
import com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id.LaunchQuery
import com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id.LaunchRocket
import com.cosmicrockets.data.network.dto.launch.request_body.LaunchSort
import com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id.LaunchRequestBodyForSearchById
import com.cosmicrockets.data.network.dto.launch.request_body.search_last.LaunchRequestBodyForSearchLast

object LaunchRequestBodyCreator {
    fun create(page:Int, rocketId: String): LaunchRequestBodyForSearchById {

        val body = LaunchRequestBodyForSearchById(
            LaunchQuery(LaunchRocket(rocketId)),
            LaunchOptions(
                MISSIONS_ON_PAGE,
                page,
                LaunchSort(SORT_TYPE)
            )
        )
        Log.e("Body", body.toString())
        return body
    }

    fun create(count: Int): LaunchRequestBodyForSearchLast {
        return LaunchRequestBodyForSearchLast(
            LaunchOptions(
                count,
                FIRST_PAGE,
                LaunchSort(SORT_TYPE)
            )
        )
    }

    private const val MISSIONS_ON_PAGE = 10
    private const val SORT_TYPE = "desc"
    private const val FIRST_PAGE = 1

}