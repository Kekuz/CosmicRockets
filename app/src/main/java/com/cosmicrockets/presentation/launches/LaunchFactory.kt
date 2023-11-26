package com.cosmicrockets.presentation.launches

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import javax.inject.Inject

class LaunchFactory(
    private val rocketId: String,
    private val searchLaunchByIdUseCase: SearchLaunchByIdUseCase,
    private val databaseInteractor: DatabaseInteractor,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return LaunchViewModel(rocketId, searchLaunchByIdUseCase, databaseInteractor) as T
    }

}