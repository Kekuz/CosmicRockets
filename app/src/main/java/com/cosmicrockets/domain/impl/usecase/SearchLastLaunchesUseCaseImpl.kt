package com.cosmicrockets.domain.impl.usecase

import com.cosmicrockets.domain.api.repository.LaunchRepository
import com.cosmicrockets.domain.api.usecase.SearchLastLaunchesUseCase
import com.cosmicrockets.domain.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchLastLaunchesUseCaseImpl(private val repository: LaunchRepository) : SearchLastLaunchesUseCase {
    override fun execute(count: Int, consumer: SearchLastLaunchesUseCase.LaunchConsumer) {
        CoroutineScope(Dispatchers.IO).launch {
            when (val resource = repository.searchLast(count)) {
                is Resource.Success -> {
                    consumer.consume(resource.data, null)
                }

                is Resource.Error -> {
                    consumer.consume(null, resource.message)
                }
            }
        }
    }


}