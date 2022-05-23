package com.example.consultivescreen.data

import com.example.consultivescreen.domain.data.DataState
import com.example.consultivescreen.domain.model.PokemonDetailDomain

interface IpokemonRepository {

    suspend fun getPokemonList(page: Int) : DataState<List<PokemonDetailDomain>>

}