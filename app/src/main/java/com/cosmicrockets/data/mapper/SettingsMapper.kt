package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.storage.dto.SettingsDto
import com.cosmicrockets.domain.models.settings.Settings

object SettingsMapper {
    fun map(input: SettingsDto): Settings {
        return Settings(
            input.height,
            input.diameter,
            input.mass,
            input.payloadWeight
        )
    }

    fun map(input: Settings): SettingsDto {
        return SettingsDto(
            input.height,
            input.diameter,
            input.mass,
            input.payloadWeight
        )
    }
}