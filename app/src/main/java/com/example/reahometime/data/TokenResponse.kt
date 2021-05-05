package com.example.reahometime.data

import com.google.gson.annotations.SerializedName


data class TokenResponse(
    @SerializedName("responseObject") val tokens: List<Token>
)


