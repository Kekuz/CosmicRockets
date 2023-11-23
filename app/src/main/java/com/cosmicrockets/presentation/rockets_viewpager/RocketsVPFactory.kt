package com.cosmicrockets.presentation.rockets_viewpager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase

class RocketsVPFactory(
    private val searchRocketsUseCase: SearchRocketsUseCase,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return RocketsVPViewModel(searchRocketsUseCase) as T
    }
}