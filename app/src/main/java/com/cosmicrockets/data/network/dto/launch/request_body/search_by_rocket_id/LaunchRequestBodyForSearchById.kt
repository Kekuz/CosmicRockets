package com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id

import com.cosmicrockets.data.network.dto.launch.request_body.LaunchOptions

data class LaunchRequestBodyForSearchById(
    val query: LaunchQuery,
    val options: LaunchOptions,
)
