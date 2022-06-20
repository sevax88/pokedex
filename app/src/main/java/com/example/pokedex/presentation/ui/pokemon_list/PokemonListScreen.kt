package com.example.pokedex.presentation.ui.pokemon_list

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.ui.theme.ConsultiveScreenTheme

@Composable
fun PokemonListScreen (
    viewModel: PokemonListViewModel
) {

    val page = viewModel.page.value

    val pokemonDetailDomains: List<PokemonDetailDomain> = viewModel.pokemons.value

    val loading = viewModel.loading.value

    val error = viewModel.error.value

    ConsultiveScreenTheme {

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar() {
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text("Pokedex", modifier = Modifier.align(CenterVertically))
                    }
                }
            }
        ) { contentPadding ->

            Log.d("recomposition", "Recomposing pokemonListScreen")

            Box(modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()) {

                PokemonList(
                    loading = loading,
                    pokemonDetailDomains = pokemonDetailDomains,
                    onTriggerNextPage = { (viewModel::onTriggerEvent)(PokemonListEvent.NextPageEvent) },
                    page = page,
                )

                if (loading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(alignment = Alignment.Center),
                        color = Color.Red
                    )
                }
            }

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