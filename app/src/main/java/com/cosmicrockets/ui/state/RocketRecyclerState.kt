package com.cosmicrockets.ui.state

sealed interface RocketRecyclerState {
    data class Height(val boolean: Boolean) : RocketRecyclerState
    data class Diameter(val boolean: Boolean) : RocketRecyclerState
    data class Mass(val boolean: Boolean) : RocketRecyclerState
    data class PayloadWeight(val boolean: Boolean) : RocketRecyclerState
}