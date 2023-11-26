package com.cosmicrockets.domain.api.repository

import com.cosmicrockets.domain.models.settings.Settings

interface SettingsRepository {

    fun saveSettings(settings: Settings)

    fun getSettings(): Settings
}