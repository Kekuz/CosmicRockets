package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.db.dto.LaunchDatabaseDto
import com.cosmicrockets.data.db.models.LaunchDatabaseEntity

object DatabaseMapper {

    fun map(input: LaunchDatabaseDto): LaunchDatabaseEntity {
        return LaunchDatabaseEntity(
            input.id,
            input.rocketId,
            input.name,
            input.date,
            input.success,
        )
    }

    fun map(input: LaunchDatabaseEntity): LaunchDatabaseDto {
        return LaunchDatabaseDto(
            input.id,
            input.rocketId,
            input.name,
            input.date,
            input.success,
        )
    }
}