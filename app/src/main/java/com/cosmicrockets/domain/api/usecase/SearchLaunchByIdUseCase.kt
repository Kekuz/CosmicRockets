package com.cosmicrockets.domain.api.usecase

import com.cosmicrockets.domain.models.launch.Launch

interface SearchLaunchByIdUseCase {
    fun execute(id: String, consumer: LaunchConsumer)

    interface LaunchConsumer {
        fun consume(foundLaunches: List<Launch>?, errorMessage: String?)
    }
}