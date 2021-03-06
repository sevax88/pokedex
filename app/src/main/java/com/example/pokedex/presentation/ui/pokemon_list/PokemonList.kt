package com.example.pokedex.presentation.ui.pokemon_list

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pokedex.domain.model.PokemonDetailDomain

@Composable
fun PokemonList(
    loading: Boolean,
    pokemonDetailDomains: List<PokemonDetailDomain>,
    onTriggerNextPage: () -> Unit,
    page: Int,
) {
    LazyColumn {
        itemsIndexed(items = pokemonDetailDomains) { index, item ->
            if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                Log.d("onList", " calling next page()")
                onTriggerNextPage()
            }
            PokemonView(pokemonDetailDomain = item, modifier = Modifier)
        }
    }
}
