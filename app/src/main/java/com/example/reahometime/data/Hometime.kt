package com.example.reahometime.data

import com.google.gson.annotations.SerializedName

data class Hometime(
    @SerializedName("Destination") val destination: String,
    @SerializedName("PredictedArrivalDateTime") val arrivalTime: String
)