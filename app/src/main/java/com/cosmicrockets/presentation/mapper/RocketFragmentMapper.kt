package com.cosmicrockets.presentation.mapper

import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.ui.rocket.RocketFragment

object RocketFragmentMapper {
    fun map(input: List<Rocket>): List<RocketFragment>{
        return input.map{ RocketFragment(it) }
    }
}