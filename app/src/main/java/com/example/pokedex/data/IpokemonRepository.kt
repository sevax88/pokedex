package com.example.pokedex.data

import android.util.Range
import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain
import kotlinx.coroutines.flow.Flow

interface IpokemonRepository {

    suspend fun getPokemonsFromNetwork(offset: Int): List<PokemonDetailDomain>
    suspend fun getAllPokemonsFromDb(): Flow<DataState<List<PokemonDetailDomain>>>
    suspend fun insertPokemonsInDb(pokemonsToInsertInDb: List<PokemonDetailDomain>)
    suspend fun isDbEmpty(): Boolean
    suspend fun getPokemonsInDb(range: IntRange): List<PokemonDetailDomain>?
}