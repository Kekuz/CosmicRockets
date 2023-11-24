package com.cosmicrockets.ui.state

import com.cosmicrockets.domain.models.launch.Launch

sealed interface LaunchesFragmentState {
    object Loading : LaunchesFragmentState
    data class Error(val message: String) : LaunchesFragmentState
    data class Content(val data: List<Launch>) : LaunchesFragmentState
}