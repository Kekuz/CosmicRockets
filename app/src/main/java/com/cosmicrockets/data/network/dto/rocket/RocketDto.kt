package com.cosmicrockets.data.network.dto.rocket

import com.google.gson.annotations.SerializedName

data class RocketDto(
    val name: String?,
    @SerializedName("flickr_images")
    val flickrImages: List<String>?,
    val height: HeightDto?,
    val diameter: DiameterDto?,
    val mass: MassDto?,
    @SerializedName("payload_weights")
    val payloadWeights: List<PayloadWeightDto>?,//Нету
    @SerializedName("first_flight")
    val firstFlight: String?,
    val country: String?,
    @SerializedName("cost_per_launch")
    val costPerLaunch: Int?,
    @SerializedName("first_stage")
    val firstStage: FirstStageDto?,//Нету
    @SerializedName("second_stage")
    val secondStage: SecondStageDto?,//Нету

    )