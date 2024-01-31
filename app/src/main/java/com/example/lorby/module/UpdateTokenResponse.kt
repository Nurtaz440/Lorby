package com.example.lorby.module

import com.google.gson.annotations.SerializedName


//@Serializable
data class UpdateTokenResponse (
    @SerializedName("access-token")
    val accessToken: String,
    @SerializedName("refresh-token")
    val refreshToken: String
)