package com.example.pokedex.network.model

import com.google.gson.annotations.SerializedName

data class PokemonDetailDto (

    @SerializedName(value = "id")
    var id: Int,

    @SerializedName(value = "name")
    var name: String,

    @SerializedName(value = "types")
    var typeDtos: List<PokemonTypeDto>,

    @SerializedName(value = "sprites")
    var pokemonSpritesDto: PokemonSpritesDto,

    @SerializedName(value = "stats")
    var listOfStats: List<StatDto>,

    @SerializedName(value = "species")
    var specieDto: SpecieDto?
)

data class SpecieDto(

    @SerializedName(value = "name")
    var specieName: String,

    @SerializedName(value = "url")
    var specieUrl : String
)

data class StatDto(

    @SerializedName(value = "base_stat")
    var baseStat : Int,

    @SerializedName(value = "stat")
    var statDetail : StatDetailDto
)

data class StatDetailDto (

    @SerializedName(value = "name")
    var statName: String,

    //Not need for now,
    @SerializedName(value = "url")
    var statUrl: String,

)

data class PokemonSpritesDto (

    @SerializedName(value = "back_default")
    var backDefault : String? = null,

    @SerializedName(value = "back_female")
    var backFemale : String? = null,

    @SerializedName(value = "back_shiny")
    var backShiny : String? = null,

    @SerializedName(value = "back_shiny_female")
    var backShinyFemale : String? = null,

    @SerializedName(value = "front_default")
    var frontDefault : String? = null,

    @SerializedName(value = "front_female")
    var frontFemale : String? = null,

    @SerializedName(value = "front_shiny")
    var frontShiny : String? = null,

    @SerializedName(value = "front_shiny_female")
    var frontShinyFemale : String? = null,

)

data class PokemonTypeDto (

    @SerializedName(value = "type")
    var type: PokemonTypeDetailInfoDto,

    @SerializedName(value = "slot")
    var slot: Int
)

data class PokemonTypeDetailInfoDto (

    @SerializedName(value = "name")
    var name: String,

    @SerializedName(value = "url")
    var url: String

)
