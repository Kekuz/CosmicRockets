package com.cosmicrockets.domain.api.usecase

import com.cosmicrockets.domain.models.rocket.Rocket

interface SearchRocketsUseCase {

    fun execute(consumer: RocketConsumer)

    interface RocketConsumer {
        fun consume(foundRockets: List<Rocket>?, errorMessage: String?)
    }
}