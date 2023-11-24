package com.cosmicrockets.data.network.dto.launch

import com.google.gson.annotations.SerializedName

data class LaunchDto(
    val rocket: String,
    val name: String?,
    @SerializedName("date_unix")
    val dateUnix: Int?,
    val success: Boolean?,
)