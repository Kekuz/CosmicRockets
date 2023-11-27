package com.cosmicrockets.data.db

import android.content.Context
import android.util.Log
import androidx.room.Room.databaseBuilder
import com.cosmicrockets.data.DatabaseClient
import com.cosmicrockets.data.db.dto.LaunchDatabaseDto
import com.cosmicrockets.data.mapper.DatabaseMapper


class RoomDatabaseClient(context: Context) : DatabaseClient {

    private var database: LaunchDatabase = databaseBuilder(
        context,
        LaunchDatabase::class.java, "launches-database"
    ).build()

    override fun save(launches: List<LaunchDatabaseDto>) {
        Log.e("Launch saved in database", launches.toString())
        database.launchDao?.insertAll(launches.map { DatabaseMapper.map(it) })
    }

    override fun getByRocketId(rocketId: String): List<LaunchDatabaseDto> {
        return database.launchDao?.getAllByRocketId(rocketId)?.map { DatabaseMapper.map(it!!) }
            ?: listOf()

    }

    override fun getById(id: String): LaunchDatabaseDto? {
        return DatabaseMapper.mapForId(database.launchDao?.getById(id))
    }
}