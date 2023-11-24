package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.network.dto.launch.LaunchDto
import com.cosmicrockets.domain.models.launch.Launch
import java.text.SimpleDateFormat
import java.util.Locale


object LaunchMapper {

    private val dateFormat =
        SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
    fun map(input: LaunchDto): Launch {
        return Launch(
            input.rocket,
            input.name ?: EMPTY_STRING,
            dateFormat.format((input.dateUnix ?: EMPTY_INT) * TO_SECONDS),
            input.success,
        )
    }

    private const val EMPTY_STRING = "-"
    private const val EMPTY_INT = 0
    private const val TO_SECONDS = 1000L
}