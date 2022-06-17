package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class ShapeDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)