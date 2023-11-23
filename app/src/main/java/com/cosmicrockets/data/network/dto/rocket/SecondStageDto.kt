package com.cosmicrockets.data.network.dto.rocket

import com.google.gson.annotations.SerializedName

data class SecondStageDto(
    val engines: Int?,
    @SerializedName("fuel_amount_tons")
    val fuelAmountTons: Double?,
    @SerializedName("burn_time_sec")
    val burnTimeSec: Int?,

)
