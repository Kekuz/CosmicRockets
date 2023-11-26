package com.cosmicrockets.data.repository

import com.cosmicrockets.data.SettingsStorage
import com.cosmicrockets.data.mapper.SettingsMapper
import com.cosmicrockets.domain.api.repository.SettingsRepository
import com.cosmicrockets.domain.models.settings.Settings

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage): SettingsRepository {
    override fun saveSettings(settings: Settings) {
        settingsStorage.save(SettingsMapper.map(settings))
    }

    override fun getSettings(): Settings {
        return SettingsMapper.map(settingsStorage.get())
    }
}