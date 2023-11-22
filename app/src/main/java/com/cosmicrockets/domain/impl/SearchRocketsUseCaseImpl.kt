package com.cosmicrockets.domain.impl

import com.cosmicrockets.domain.api.repository.RocketRepository
import com.cosmicrockets.domain.api.usecase.SearchRocketsUseCase
import com.cosmicrockets.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchRocketsUseCaseImpl(private val repository: RocketRepository): SearchRocketsUseCase {
    override fun execute(consumer: SearchRocketsUseCase.RocketConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val resource = repository.search()) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }

}