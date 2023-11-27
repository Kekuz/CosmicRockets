package com.cosmicrockets.data.network.dto.launch.request_body

import com.cosmicrockets.data.network.dto.launch.request_body.LaunchSort

data class LaunchOptions(
    val limit: Int,
    val page: Int,
    val sort: LaunchSort,
)
