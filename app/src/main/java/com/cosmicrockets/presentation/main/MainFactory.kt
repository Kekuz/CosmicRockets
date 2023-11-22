package com.cosmicrockets.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase

class MainFactory(
    private val searchRocketsUseCase: SearchRocketsUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(searchRocketsUseCase) as T
    }
}