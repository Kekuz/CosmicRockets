package com.cosmicrockets.data.repository

import com.cosmicrockets.data.DatabaseClient
import com.cosmicrockets.data.mapper.LaunchMapper
import com.cosmicrockets.domain.api.repository.DatabaseRepository
import com.cosmicrockets.domain.models.launch.Launch

class DatabaseRepositoryImpl(private val databaseClient: DatabaseClient): DatabaseRepository {
    override fun saveLaunches(launches: List<Launch>) {
        databaseClient.save(launches.map { LaunchMapper.map(it) })
    }

    override fun getLaunches(rocketId: String): List<Launch> {
        return databaseClient.get(rocketId).map { LaunchMapper.map(it) }
    }
}