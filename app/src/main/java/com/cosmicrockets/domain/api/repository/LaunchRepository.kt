package com.cosmicrockets.domain.api.repository

import com.cosmicrockets.domain.models.launch.LaunchResponse
import com.cosmicrockets.domain.util.Resource

interface LaunchRepository {
    fun searchByRocketId(page: Int, rocketId: String): Resource<LaunchResponse>

    fun searchLast(count: Int): Resource<LaunchResponse>
}