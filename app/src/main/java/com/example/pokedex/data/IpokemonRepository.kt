package com.example.pokedex.data

import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain

interface IpokemonRepository {

    suspend fun getPokemonList(page: Int) : DataState<List<PokemonDetailDomain>>

}