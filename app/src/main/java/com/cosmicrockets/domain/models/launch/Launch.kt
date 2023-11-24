package com.cosmicrockets.domain.models.launch

data class Launch(
    val rocketId: String,
    var name: String,
    val date: String,
    val success: Boolean,
)