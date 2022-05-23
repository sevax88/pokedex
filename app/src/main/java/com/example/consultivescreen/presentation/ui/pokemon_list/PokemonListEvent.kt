package com.example.consultivescreen.presentation.ui.pokemon_list

sealed class PokemonListEvent {

    object NextPageEvent: PokemonListEvent()

    object StartEvent: PokemonListEvent()

}
