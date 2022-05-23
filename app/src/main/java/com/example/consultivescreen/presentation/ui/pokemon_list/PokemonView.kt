package com.example.consultivescreen.presentation.ui.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.consultivescreen.domain.model.PokemonDetailDomain

@Composable
fun PokemonView(
    pokemonDetailDomain : PokemonDetailDomain
) {
    Row(modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonDetailDomain.image)
                .build(),
            contentDescription = "pokemon image",
            modifier = Modifier
                .size(width = 120.dp, height = 120.dp),
            contentScale = ContentScale.Crop,
        )


        Column(verticalArrangement = Arrangement.SpaceBetween) {
            Text(text = pokemonDetailDomain.name)
            Text(text = pokemonDetailDomain.type, modifier = Modifier.padding(top = 10.dp))
            Text(text = pokemonDetailDomain.id.toString())
        }
        Spacer(modifier = Modifier.weight(1.0f))
        Icon(Icons.Filled.ArrowForward, contentDescription = "arrow", modifier = Modifier.padding(end = 10.dp))

    }
}

@Preview
@Composable
fun PokemonViewPreview() {
    val pokemon = PokemonDetailDomain(id = 1, name = "Bulbasaur", type = "Plant", image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
    PokemonView(pokemonDetailDomain = pokemon)
}