package com.cosmicrockets.domain.api.usecase

import com.cosmicrockets.domain.models.launch.LaunchResponse

interface SearchLastLaunchesUseCase {
    fun execute(count:Int, consumer: LaunchConsumer)

    interface LaunchConsumer {
        fun consume(launchResponse: LaunchResponse?, errorMessage: String?)
    }
}