package com.cosmicrockets.presentation.mapper

import android.util.Log
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.models.RocketInfo

object RocketInfoMapper {
    fun map(rocket: Rocket): RocketInfo {
        val rocketInfo =
            RocketInfo(
                rocket.name,
                rocket.image,
                checkNullNumber(rocket.height.meters),
                checkNullNumber(rocket.height.feet),
                checkNullNumber(rocket.diameter.meters),
                checkNullNumber(rocket.diameter.feet),
                checkNullNumber(rocket.mass.kg),
                checkNullNumber(rocket.mass.lb),
                checkNullNumber(rocket.payloadWeight.kg),
                checkNullNumber(rocket.payloadWeight.lb),
                rocket.firstFlight,
                rocket.country,
                checkNullNumber(rocket.costPerLaunch),
                checkNullNumber(rocket.firstStage.engines),
                checkNullNumber(rocket.firstStage.fuelAmountTons),
                checkNullNumber(rocket.firstStage.burnTimeSec),
                checkNullNumber(rocket.secondStage.engines),
                checkNullNumber(rocket.secondStage.fuelAmountTons),
                checkNullNumber(rocket.secondStage.burnTimeSec),
            )
        Log.e("RocketInfo", rocketInfo.toString())
        return rocketInfo
    }

    private fun checkNullNumber(num: Number): String =
        if (num == -1) {
            EMPTY_FIELD
        } else {
            num.toString()
        }

    private const val EMPTY_FIELD = "-"

}