package com.cosmicrockets.presentation.mapper

import android.util.Log
import com.cosmicrockets.domain.models.rocket.Rocket
import com.cosmicrockets.presentation.models.RocketInfo
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

object RocketInfoMapper {

    private val dateFormat =
        SimpleDateFormat("d MMMM, yyyy", Locale("ru"))

    fun map(rocket: Rocket): RocketInfo {
        val rocketInfo =
            RocketInfo(
                rocket.id,
                rocket.name,
                rocket.image,
                checkNull(rocket.height.meters),
                checkNull(rocket.height.feet),
                checkNull(rocket.diameter.meters),
                checkNull(rocket.diameter.feet),
                convertToCommaFormat(rocket.mass.kg),
                convertToCommaFormat(rocket.mass.lb),
                convertToCommaFormat(rocket.payloadWeight.kg),
                convertToCommaFormat(rocket.payloadWeight.lb),
                toDate(rocket.firstFlight)?.let { dateFormat.format(it) } ?: EMPTY_FIELD,
                rocket.country,
                convertMoneySum(rocket.costPerLaunch),
                checkNull(rocket.firstStage.engines),
                checkNull(rocket.firstStage.fuelAmountTons).replace('.', ','),
                checkNull(rocket.firstStage.burnTimeSec),
                checkNull(rocket.secondStage.engines),
                checkNull(rocket.secondStage.fuelAmountTons).replace('.', ','),
                checkNull(rocket.secondStage.burnTimeSec),
            )
        Log.e("RocketInfo", rocketInfo.toString())
        return rocketInfo
    }

    private fun checkNull(num: Number?): String =
        num?.toString() ?: EMPTY_FIELD

    private fun convertMoneySum(int: Int?): String =
        if (int == null) {
            EMPTY_FIELD
        } else {
            "\$${(int / TO_MILLIONS).roundToInt()} млн"
        }

    private fun convertToCommaFormat(int: Int?): String =
        if (int == null) {
            EMPTY_FIELD
        } else {
            NumberFormat.getInstance(Locale.ENGLISH).format(int)
        }


    private fun toDate(date: String): Date? {
        return try {
            SimpleDateFormat("yyyy-mm-dd", Locale.getDefault()).parse(date)
        } catch (e: Exception) {
            null
        }

    }

    private const val EMPTY_FIELD = "-"
    private const val TO_MILLIONS = 1_000_000.0

}