package com.cosmicrockets.domain.api.usecase

import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.domain.models.launch.LaunchResponse

interface SearchLaunchByIdUseCase {
    fun execute(page:Int, rocketId: String, consumer: LaunchConsumer)

    interface LaunchConsumer {
        fun consume(launchResponse: LaunchResponse?, errorMessage: String?)
    }
}