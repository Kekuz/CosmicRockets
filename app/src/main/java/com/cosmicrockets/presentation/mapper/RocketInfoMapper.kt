package com.cosmicrockets.presentation.mapper

import android.util.Log
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.models.RocketInfo

object RocketInfoMapper {
    fun map(rocket: Rocket):RocketInfo{
        val rocketInfo =
         RocketInfo(
            rocket.name,
            rocket.image,
            rocket.height.meters.toString(),
            rocket.height.feet.toString(),
            rocket.diameter.meters.toString(),
            rocket.diameter.feet.toString(),
            rocket.mass.kg.toString(),
            rocket.mass.lb.toString(),
            rocket.payloadWeight.kg.toString(),
            rocket.payloadWeight.lb.toString(),
            rocket.firstFlight,
            rocket.country,
            rocket.costPerLaunch.toString(),
            rocket.firstStage.engines.toString(),
            rocket.firstStage.fuelAmountTons.toString(),
            rocket.firstStage.burnTimeSec.toString(),
            rocket.secondStage.engines.toString(),
            rocket.secondStage.fuelAmountTons.toString(),
            rocket.secondStage.burnTimeSec.toString(),
        )
        Log.e("RocketInfo",rocketInfo.toString())
        return rocketInfo
    }
}