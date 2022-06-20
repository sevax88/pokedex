package com.example.pokedex.presentation.navigation

sealed class Screen (
    val route: String,
) {

    object PokemonList: Screen("pokemonList")

}