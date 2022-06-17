package com.example.consultivescreen.domain.model

data class PokemonDetailDomain(
    val id: Int,
    val name: String,
    val image: String,
    val types: List<String>,
    val listOfStats: List<StatDomain>,
    val specie: SpecieDomain? = null,
)