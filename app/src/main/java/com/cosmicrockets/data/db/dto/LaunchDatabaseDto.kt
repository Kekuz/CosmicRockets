package com.cosmicrockets.data.db.dto


data class LaunchDatabaseDto(
    val id: String,
    val rocketId: String,
    val name: String,
    val date: String,
    val success: Boolean?,
)