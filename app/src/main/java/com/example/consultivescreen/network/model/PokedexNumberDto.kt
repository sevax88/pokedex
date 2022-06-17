package com.example.consultivescreen.network.model


import com.google.gson.annotations.SerializedName

data class PokedexNumberDto(
    @SerializedName("entry_number")
    val entryNumber: Int,
    @SerializedName("pokedex")
    val pokedex: PokedexDto
)