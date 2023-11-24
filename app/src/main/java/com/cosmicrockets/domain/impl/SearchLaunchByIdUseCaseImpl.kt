package com.cosmicrockets.domain.impl

import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLaunchByIdUseCaseImpl(): SearchLaunchByIdUseCase {
    override fun execute(id: String, consumer: SearchLaunchByIdUseCase.LaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            /*when(val resource = repository.search()) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }*/
        }
    }
}