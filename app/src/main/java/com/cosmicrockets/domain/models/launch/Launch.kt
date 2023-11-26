package com.cosmicrockets.domain.models.launch

data class Launch(
    val id: String,
    val rocketId: String,
    var name: String,
    val date: String,
    val success: Boolean?,
)