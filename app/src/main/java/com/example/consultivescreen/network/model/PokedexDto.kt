package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class PokedexDto(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String
)