package com.example.consultivescreen.presentation.ui.pokemon_list

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.consultivescreen.presentation.ui.pokemon_list.PokemonList
import com.example.consultivescreen.domain.model.PokemonDetailDomain

@Composable
fun PokemonListScreen (
    viewModel: PokemonListViewModel
) {

    val page = viewModel.page.value

    val pokemonDetailDomains: List<PokemonDetailDomain> = viewModel.pokemons.value

    val loading = viewModel.loading.value

    val error = viewModel.error.value

    Column() {
        Button(onClick = {
            viewModel.onTriggerEvent(PokemonListEvent.StartEvent)
        }) {
            Text(text = "Click Me!")
        }
    }

    PokemonList(
        loading = loading,
        pokemonDetailDomains = pokemonDetailDomains,
        onTriggerNextPage = { viewModel.onTriggerEvent(PokemonListEvent.NextPageEvent)},
        page = page,
    )
}