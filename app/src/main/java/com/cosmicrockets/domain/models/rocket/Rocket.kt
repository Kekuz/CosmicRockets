package com.cosmicrockets.domain.models.rocket

data class Rocket(
    val id: String,
    val name: String,
    val image: String,
    val height: Height,
    val diameter: Diameter,
    val mass: Mass,
    val payloadWeight: PayloadWeight,
    val firstFlight: String,
    val country: String,
    val costPerLaunch: Int?,
    val firstStage: FirstStage,
    val secondStage: SecondStage,

)