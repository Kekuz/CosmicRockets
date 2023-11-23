package com.cosmicrockets.presentation.models

import android.os.Parcel
import android.os.Parcelable


data class RocketInfo(
    val name: String?,
    val image: String?,
    val heightMeters: String?,
    val heightFeet: String?,
    val diameterMeters: String?,
    val diameterFeet: String?,
    val massKg: String?,
    val massLb: String?,
    val payloadWeightKg: String?,
    val payloadWeightLb: String?,
    val firstFlight: String?,
    val country: String?,
    val costPerLaunch: String?,
    val firstEngines: String?,
    val firstFuelAmountTons: String?,
    val firstBurnTimeSec: String?,
    val secondEngines: String?,
    val secondFuelAmountTons: String?,
    val secondBurnTimeSec: String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(heightMeters)
        parcel.writeString(heightFeet)
        parcel.writeString(diameterMeters)
        parcel.writeString(diameterFeet)
        parcel.writeString(massKg)
        parcel.writeString(massLb)
        parcel.writeString(payloadWeightKg)
        parcel.writeString(payloadWeightLb)
        parcel.writeString(firstFlight)
        parcel.writeString(country)
        parcel.writeString(costPerLaunch)
        parcel.writeString(firstEngines)
        parcel.writeString(firstFuelAmountTons)
        parcel.writeString(firstBurnTimeSec)
        parcel.writeString(secondEngines)
        parcel.writeString(secondFuelAmountTons)
        parcel.writeString(secondBurnTimeSec)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<RocketInfo> {
        override fun createFromParcel(parcel: Parcel): RocketInfo {
            return RocketInfo(parcel)
        }

        override fun newArray(size: Int): Array<RocketInfo?> {
            return arrayOfNulls(size)
        }
    }
}
