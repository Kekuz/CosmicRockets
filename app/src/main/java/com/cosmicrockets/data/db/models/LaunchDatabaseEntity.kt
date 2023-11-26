package com.cosmicrockets.data.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("launch_database")
data class LaunchDatabaseEntity(
    @PrimaryKey
    val id: String,
    val rocketId: String,
    val name: String,
    val date: String,
    val success: Boolean?,
)