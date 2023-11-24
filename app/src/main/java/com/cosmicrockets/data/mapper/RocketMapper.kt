package com.cosmicrockets.data.mapper

import com.cosmicrockets.data.network.dto.rocket.DiameterDto
import com.cosmicrockets.data.network.dto.rocket.FirstStageDto
import com.cosmicrockets.data.network.dto.rocket.HeightDto
import com.cosmicrockets.data.network.dto.rocket.MassDto
import com.cosmicrockets.data.network.dto.rocket.PayloadWeightDto
import com.cosmicrockets.data.network.dto.rocket.RocketDto
import com.cosmicrockets.data.network.dto.rocket.SecondStageDto
import com.cosmicrockets.domain.models.rocket.Diameter
import com.cosmicrockets.domain.models.rocket.FirstStage
import com.cosmicrockets.domain.models.rocket.Height
import com.cosmicrockets.domain.models.rocket.Mass
import com.cosmicrockets.domain.models.rocket.PayloadWeight
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.domain.models.rocket.SecondStage

object RocketMapper {
    fun map(input: RocketDto): Rocket {
        return Rocket(
            input.id,
            input.name ?: EMPTY_STRING,
            input.flickrImages?.get(0) ?: EMPTY_STRING,
            heightMapper(input.height),
            diameterMapper(input.diameter),
            massMapper(input.mass),
            payloadWeightMapper(input.payloadWeights),
            input.firstFlight ?: EMPTY_STRING,
            input.country ?: EMPTY_STRING,
            input.costPerLaunch,
            firstStageMapper(input.firstStage),
            secondStageMapper(input.secondStage),
        )
    }

    private fun heightMapper(input: HeightDto?): Height {
        return Height(
            input?.meters,
            input?.feet,
        )
    }

    private fun diameterMapper(input: DiameterDto?): Diameter {
        return Diameter(
            input?.meters,
            input?.feet,
        )
    }

    private fun massMapper(input: MassDto?): Mass {
        return Mass(
            input?.kg,
            input?.lb,
        )
    }

    private fun payloadWeightMapper(input: List<PayloadWeightDto>?): PayloadWeight {
        val payloadWeightDto = input?.find { it.id == LOW_EARTH_ORBIT }
        return PayloadWeight(
            payloadWeightDto?.kg ?: 0,
            payloadWeightDto?.lb ?: 0,
        )
    }

    private fun firstStageMapper(input: FirstStageDto?): FirstStage {
        return FirstStage(
            input?.engines,
            input?.fuelAmountTons,
            input?.burnTimeSec,
        )
    }

    private fun secondStageMapper(input: SecondStageDto?): SecondStage {
        return SecondStage(
            input?.engines,
            input?.fuelAmountTons,
            input?.burnTimeSec,
        )
    }

    private const val LOW_EARTH_ORBIT = "leo"
    private const val EMPTY_STRING = "-"
}