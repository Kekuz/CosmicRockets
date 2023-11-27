package com.cosmicrockets.domain.api.repository

import com.cosmicrockets.domain.models.launch.Launch

interface DatabaseRepository {
    fun saveLaunches(launches: List<Launch>)

    fun getLaunches(rocketId: String): List<Launch>

    fun getLaunchById(id: String): Launch?
}