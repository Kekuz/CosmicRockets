package com.cosmicrockets.domain.models.launch

data class LaunchResponse(
    val docs: List<Launch>,
    val page: Int,
    val hasNextPage: Boolean,
)