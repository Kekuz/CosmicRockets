package com.cosmicrockets.domain.models.launch

data class Launch(
    val rocketId: String,
    val name: String,
    val date: String,
    val success: Boolean,
)