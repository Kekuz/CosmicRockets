package com.cosmicrockets.data.network.dto.launch.request_body

import com.google.gson.annotations.SerializedName

data class LaunchSort(
    @SerializedName("date_utc")
    val dateUtc: String
)
