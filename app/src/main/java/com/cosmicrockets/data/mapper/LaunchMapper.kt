package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.db.dto.LaunchDatabaseDto
import com.cosmicrockets.data.db.models.LaunchDatabaseEntity
import com.cosmicrockets.data.network.dto.launch.LaunchDto
import com.cosmicrockets.domain.models.launch.Launch
import java.text.SimpleDateFormat
import java.util.Locale


object LaunchMapper {

    private val dateFormat =
        SimpleDateFormat("d MMMM, yyyy", Locale("ru"))
    fun map(input: LaunchDto): Launch {
        return Launch(
            input.id,
            input.rocket,
            input.name ?: EMPTY_STRING,
            dateFormat.format((input.dateUnix ?: EMPTY_INT) * TO_SECONDS),
            input.success,
        )
    }

    fun map(input: Launch): LaunchDatabaseDto {
        return LaunchDatabaseDto(
            input.id,
            input.rocketId,
            input.name,
            input.date,
            input.success,
        )
    }

    fun map(input: LaunchDatabaseDto): Launch {
        return Launch(
            input.id,
            input.rocketId,
            input.name,
            input.date,
            input.success,
        )
    }

    fun mapForId(input: LaunchDatabaseDto?): Launch? {
        return  if(input == null){
            null
        }else{
            Launch(
                input.id,
                input.rocketId,
                input.name,
                input.date,
                input.success,
            )
        }

    }


    private const val EMPTY_STRING = "-"
    private const val EMPTY_INT = 0
    private const val TO_SECONDS = 1000L
}