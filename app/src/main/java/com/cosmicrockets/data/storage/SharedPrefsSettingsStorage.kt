package com.cosmicrockets.data.storage

import android.content.Context
import com.cosmicrockets.data.SettingsStorage
import com.cosmicrockets.data.storage.dto.SettingsDto

class SharedPrefsSettingsStorage(context: Context) : SettingsStorage {
    private val sharedPreferences = context.getSharedPreferences(SETTINGS, Context.MODE_PRIVATE)
    override fun save(settings: SettingsDto) {
        sharedPreferences.edit().apply {
            putBoolean(HEIGHT, settings.height)
            putBoolean(DIAMETER, settings.diameter)
            putBoolean(MASS, settings.mass)
            putBoolean(PAYLOAD_WEIGHT, settings.payloadWeight)
        }.apply()

    }

    override fun get(): SettingsDto {
        return SettingsDto(
            sharedPreferences.getBoolean(
                HEIGHT, DEFAULT_NIGHT_MODE
            ),
            sharedPreferences.getBoolean(
                DIAMETER, DEFAULT_NIGHT_MODE
            ),
            sharedPreferences.getBoolean(
                MASS, DEFAULT_NIGHT_MODE
            ),
            sharedPreferences.getBoolean(
                PAYLOAD_WEIGHT, DEFAULT_NIGHT_MODE
            ),
        )
    }

    private companion object {
        const val SETTINGS = "settings"
        const val HEIGHT = "height"
        const val DIAMETER = "diameter"
        const val MASS = "mass"
        const val PAYLOAD_WEIGHT = "payload_weight"
        const val DEFAULT_NIGHT_MODE = false
    }

}