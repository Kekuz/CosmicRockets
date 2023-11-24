package com.cosmicrockets.presentation.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase

class LaunchFactory(
    private val id: String,
    private val searchLaunchByIdUseCase: SearchLaunchByIdUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchViewModel(id, searchLaunchByIdUseCase) as T
    }
}