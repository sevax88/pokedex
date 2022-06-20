package com.example.pokedex.network.model


import com.google.gson.annotations.SerializedName

data class EvolutionChainDto(
    @SerializedName("url")
    val url: String
)