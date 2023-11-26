package com.cosmicrockets.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cosmicrockets.data.db.models.LaunchDatabaseEntity


@Dao
interface LaunchDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(launches: List<LaunchDatabaseEntity>)
    @Query("SELECT * FROM launch_database WHERE rocketId LIKE :rocketId")
    fun getAllByRocketId(rocketId: String?): List<LaunchDatabaseEntity?>?

    /*@Insert
    fun insert(launch: Launch?)

    @Delete
    fun delete(launch: Launch?)*/


}