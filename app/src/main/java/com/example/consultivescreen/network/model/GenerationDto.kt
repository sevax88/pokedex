package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class GenerationDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)