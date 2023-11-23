package com.cosmicrockets.ui.state

import com.cosmicrockets.ui.rocket.RocketFragment


sealed interface RocketsVPFragmentState {
    object Loading : RocketsVPFragmentState
    data class Error(val message: String) : RocketsVPFragmentState
    data class Content(val data: List<RocketFragment>) : RocketsVPFragmentState
}