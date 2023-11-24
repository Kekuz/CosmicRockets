package com.cosmicrockets.data.network.dto.launch.launch_request_body

import com.google.gson.annotations.SerializedName

data class LaunchRocket(
    @SerializedName("\$eq")
    val eq: String
)
