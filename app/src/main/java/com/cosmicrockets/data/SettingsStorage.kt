package com.cosmicrockets.data

import com.cosmicrockets.data.storage.dto.SettingsDto

interface SettingsStorage {
    fun save(settings: SettingsDto)

    fun get(): SettingsDto
}