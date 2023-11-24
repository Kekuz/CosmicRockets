package com.cosmicrockets.data.network.dto.launch.launch_request_body

data class LaunchOptions(
    val limit: Int,
    val page: Int,
    val sort: LaunchSort,
)
