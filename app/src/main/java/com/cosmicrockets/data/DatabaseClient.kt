package com.cosmicrockets.data

import com.cosmicrockets.data.db.dto.LaunchDatabaseDto

interface DatabaseClient {
    fun save(launches: List<LaunchDatabaseDto>)

    fun getByRocketId(rocketId: String): List<LaunchDatabaseDto>

    fun getById(id: String): LaunchDatabaseDto?

}