package com.cosmicrockets.data.network.dto.rocket

import com.cosmicrockets.data.network.dto.Response

data class RocketSearchResponse(
    val docs: List<RocketDto>,
) : Response()