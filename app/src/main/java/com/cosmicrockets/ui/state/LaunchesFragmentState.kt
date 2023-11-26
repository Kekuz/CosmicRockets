package com.cosmicrockets.ui.state

import com.cosmicrockets.domain.models.launch.Launch

sealed interface LaunchesFragmentState {
    object Loading : LaunchesFragmentState
    object LoadingBottom : LaunchesFragmentState
    data class Error(val data: List<Launch>, val message: String) : LaunchesFragmentState
    data class Content(val data: List<Launch>) : LaunchesFragmentState
}