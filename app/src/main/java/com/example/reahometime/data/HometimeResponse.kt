package com.example.reahometime.data

import com.google.gson.annotations.SerializedName

data class HometimeResponse(
    @SerializedName("responseObject") val hometimes: List<Hometime>
)