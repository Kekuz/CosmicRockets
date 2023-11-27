package com.cosmicrockets.domain.impl.interactor

import android.util.Log
import com.cosmicrockets.domain.api.interactor.DatabaseInteractor
import com.cosmicrockets.domain.api.repository.DatabaseRepository
import com.cosmicrockets.domain.models.launch.Launch
import com.cosmicrockets.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DatabaseInteractorImpl(private val repository: DatabaseRepository):DatabaseInteractor {
    override fun saveLaunches(launches: List<Launch>) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.saveLaunches(launches)
        }

    }

    override fun getLaunches(rocketId: String, consumer: DatabaseInteractor.DatabaseConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            consumer.consume(repository.getLaunches(rocketId))
        }
    }

    override fun getLaunchById(id: String, consumer: DatabaseInteractor.DatabaseLaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            consumer.consume(repository.getLaunchById(id))
        }
    }

}