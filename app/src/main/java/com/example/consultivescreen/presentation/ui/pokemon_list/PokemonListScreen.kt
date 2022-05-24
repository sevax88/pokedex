package com.example.consultivescreen.presentation.ui.pokemon_list

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.consultivescreen.domain.model.PokemonDetailDomain
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun PokemonListScreen (
    viewModel: PokemonListViewModel 
) {

    val page = viewModel.page.value

    val pokemonDetailDomains: List<PokemonDetailDomain> = viewModel.pokemons.value

    val loading = viewModel.loading.value

    val error = viewModel.error.value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar {
                Text("Pokedex")
            }
        }
    ) { contentPadding ->

        Log.d("recomposition", "Recomposing pokemonListScreen")

        Box(modifier = Modifier.padding(contentPadding).fillMaxSize()) {

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }

            PokemonList(
                loading = loading,
                pokemonDetailDomains = pokemonDetailDomains,
                onTriggerNextPage = {viewModel.onTriggerEvent(PokemonListEvent.NextPageEvent)} ,
                page = page,
            )
        }

    }
}

//@Preview
//@Composable
//fun PokemonListPreview(){
//    PokemonListScreen(viewModel = HiltViewModel)
//
//
//}