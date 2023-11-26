package com.cosmicrockets.presentation.mapper

import com.cosmicrockets.domain.models.settings.Settings
import com.cosmicrockets.presentation.models.RocketDataRV

object SettingsFromRocketDataMapper {
    fun map(input: List<RocketDataRV>): Settings {
        return Settings(
            input[0].infoUtil == "ft",
            input[1].infoUtil == "ft",
            input[2].infoUtil == "lb",
            input[3].infoUtil == "lb",
        )
    }
}