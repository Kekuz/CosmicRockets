package com.cosmicrockets.domain.api.interactor

import com.cosmicrockets.domain.models.settings.Settings

interface SettingsSavingInteractor {
    fun saveSettings(settings: Settings)

    fun getSettings(): Settings
}