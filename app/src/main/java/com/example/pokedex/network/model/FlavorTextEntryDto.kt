package com.example.pokedex.network.model


import com.google.gson.annotations.SerializedName

data class FlavorTextEntryDto(
    @SerializedName("flavor_text")
    val flavorText: String,
)