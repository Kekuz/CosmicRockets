package com.cosmicrockets.data.network.dto.launch.request_body.search_by_rocket_id

import com.google.gson.annotations.SerializedName

data class LaunchRocket(
    @SerializedName("\$eq")
    val eq: String
)
