package com.example.pokedex.domain.use_cases.pokemon_list

import com.example.pokedex.data.IpokemonRepository
import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

class GetPokemonsFirstPage(
    private val repository: IpokemonRepository
) {
    suspend fun execute() : Flow<DataState<List<PokemonDetailDomain>>> {

        return withContext(Dispatchers.IO){

            if (repository.isDbEmpty()){
                val pokesFromNetwork = repository.getPokemonsFromNetwork(offset = 0)
                repository.insertPokemonsInDb(pokesFromNetwork)
            }

            return@withContext repository.getAllPokemonsFromDb()

        }
    }
}
