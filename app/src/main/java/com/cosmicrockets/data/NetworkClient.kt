package com.cosmicrockets.data

import com.cosmicrockets.data.network.dto.Response

interface NetworkClient {
    fun doRequest(dto: Any): Response
}