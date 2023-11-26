package com.cosmicrockets.domain.impl.interactor

import com.cosmicrockets.domain.api.interactor.SettingsSavingInteractor
import com.cosmicrockets.domain.api.repository.SettingsRepository
import com.cosmicrockets.domain.models.settings.Settings

class SettingsSavingInteractorImpl(private val repository: SettingsRepository) : SettingsSavingInteractor {
    override fun saveSettings(settings: Settings){
        repository.saveSettings(settings)
    }

    override fun getSettings(): Settings{
        return repository.getSettings()
    }
}