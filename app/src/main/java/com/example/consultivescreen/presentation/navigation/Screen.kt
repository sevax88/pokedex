package com.example.consultivescreen.presentation.navigation

sealed class Screen (
    val route: String,
) {

    object PokemonList: Screen("pokemonList")

}