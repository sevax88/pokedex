package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class VarietyDto(
    @SerializedName("is_default")
    val isDefault: Boolean,
    @SerializedName("pokemon")
    val pokemon: PokemonDto
)