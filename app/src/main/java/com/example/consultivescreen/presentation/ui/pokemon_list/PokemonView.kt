package com.example.consultivescreen.presentation.ui.pokemon_list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.domain.model.StatDomain
import com.example.consultivescreen.util.typesColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun PokemonView(
    modifier: Modifier,
    pokemonDetailDomain : PokemonDetailDomain
) {
    Card(
        onClick = { /*TODO*/ },
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .width(120.dp),
        elevation = 8.dp,
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(modifier = Modifier.padding(4.dp)) {
                Row(modifier = Modifier.padding(8.dp)) {
                    Text(
                        text = "# " + pokemonDetailDomain.id.toString(),
                        modifier = Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.h6
                    )
                    Text(
                        text = pokemonDetailDomain.name.capitalize(),
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.h6
                    )
                }

                Row(modifier = Modifier.padding(8.dp)) {
                    OutlinedButton(
                        onClick = { },
                        border = BorderStroke(1.dp, Color.White),
                        shape = RoundedCornerShape(40)
                    ) {
                        Text(
                            text = pokemonDetailDomain.types.first(),
                            color = Color.White,
                            style = MaterialTheme.typography.body2
                        )
                    }

                    if (pokemonDetailDomain.types.size > 1) {
                        OutlinedButton(
                            modifier = Modifier.padding(start = 8.dp),
                            onClick = { },
                            border = BorderStroke(1.dp, Color.White),
                            shape = RoundedCornerShape(40)
                        ) {
                            Text(
                                text = pokemonDetailDomain.types[1],
                                color = Color.White,
                                style = MaterialTheme.typography.body2
                            )
                        }
                    }
                }

            }
            Box(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = typesColors[pokemonDetailDomain.types.first()]
                                ?: Color.White,
                            radius = 150f
                        )
                    }
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(pokemonDetailDomain.image)
                        .build(),
                    contentDescription = "pokemon image",
                    modifier = Modifier
                        .size(width = 120.dp, height = 120.dp)
                        .align(Alignment.CenterEnd),
                    contentScale = ContentScale.Fit,
                )
            }

        }
    }
}


@Preview
@Composable
fun PokemonViewPreview() {
    val pokemon = PokemonDetailDomain(
        id = 1,
        name = "bulbasaur",
        types = listOf("Plant", "Dark"),
        image = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png",
        listOfStats = listOf(StatDomain(name = "hp", value = 15))
    )
    PokemonView(pokemonDetailDomain = pokemon, modifier = Modifier.background(color = Color.Red))
}