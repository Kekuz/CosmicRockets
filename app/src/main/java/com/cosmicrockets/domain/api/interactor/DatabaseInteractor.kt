package com.cosmicrockets.domain.api.interactor

import com.cosmicrockets.domain.models.launch.Launch

interface DatabaseInteractor {
    fun saveLaunches(launches: List<Launch>)

    fun getLaunches(rocketId: String, consumer: DatabaseConsumer)

    interface DatabaseConsumer {
        fun consume(foundLaunches: List<Launch>)
    }
}