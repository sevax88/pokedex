package com.example.pokedex.network.model

import com.google.gson.annotations.SerializedName


data class PokemonsDto (

    @SerializedName(value = "results")
    var listOfPokes: List<PokemonDto>

)

data class PokemonDto (

    @SerializedName(value = "name")
    var name: String,

    @SerializedName(value = "url")
    var url: String

)