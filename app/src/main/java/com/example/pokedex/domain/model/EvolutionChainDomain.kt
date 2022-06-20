package com.example.pokedex.domain.model


data class EvolutionChainDomain(
    val listOfEvolutionedPokesFromThisOne : List<PokemonDomain>
)
