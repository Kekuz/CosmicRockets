package com.cosmicrockets.domain.impl

import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.api.usecase.SearchLaunchByIdUseCase
import com.cosmicrockets.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLaunchByIdUseCaseImpl(private val repository: LaunchRepository): SearchLaunchByIdUseCase {
    override fun execute(page: Int, rocketId: String, consumer: SearchLaunchByIdUseCase.LaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when(val resource = repository.search(page, rocketId)) {
                is Resource.Success -> { consumer.consume(resource.data, null) }
                is Resource.Error -> { consumer.consume(null, resource.message) }
            }
        }
    }
}