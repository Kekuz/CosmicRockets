package com.cosmicrockets.domain.api.repository

import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.domain.util.Resource

interface RocketRepository {
    fun search(): Resource<List<Rocket>>
}