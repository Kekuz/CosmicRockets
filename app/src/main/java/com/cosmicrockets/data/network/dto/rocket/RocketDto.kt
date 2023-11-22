package com.cosmicrockets.data.network.dto.rocket

import com.google.gson.annotations.SerializedName

data class RocketDto(
    val name: String?,
    @SerializedName("flickr_images")
    val flickrImages: List<String>?,
    val height: HeightDto?,
    val diameter: DiameterDto?,
    val mass: MassDto?,
    val payloadWeights: List<PayloadWeightDto>?,
    val firstFlight: String?,
    val country: String?,
    val costPerLaunch: Int?,
    val firstStage: FirstStageDto?,
    val secondStage: SecondStageDto?,

    )