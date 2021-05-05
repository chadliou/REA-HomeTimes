package com.example.reahometime.data

import com.google.gson.annotations.SerializedName

data class Token(
    @SerializedName("DeviceToken") val deviceToken: String
)