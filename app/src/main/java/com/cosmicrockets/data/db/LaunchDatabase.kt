package com.cosmicrockets.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cosmicrockets.data.db.dao.LaunchDao
import com.cosmicrockets.data.db.models.LaunchDatabaseEntity


@Database(
    entities = [LaunchDatabaseEntity::class],
    version = 1
)
abstract class LaunchDatabase : RoomDatabase() {
    abstract val launchDao: LaunchDao?
}