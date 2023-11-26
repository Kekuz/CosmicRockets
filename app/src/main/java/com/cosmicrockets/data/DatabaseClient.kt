package com.cosmicrockets.data

import com.cosmicrockets.data.db.dto.LaunchDatabaseDto

interface DatabaseClient {
    fun save(launches: List<LaunchDatabaseDto>)

    fun get(rocketId: String): List<LaunchDatabaseDto>

}