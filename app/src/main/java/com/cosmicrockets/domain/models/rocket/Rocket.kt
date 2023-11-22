package com.cosmicrockets.domain.models.rocket

data class Rocket(
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    val payloadWeights: List<PayloadWeight>,
    val firstFlight: String,
    val country: String,
    val costPerLaunch: Int,
    val firstStage: FirstStage,
    val secondStage: SecondStage,

)