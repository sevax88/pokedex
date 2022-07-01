package com.example.pokedex.domain.use_cases.pokemon_list

import com.example.pokedex.data.IpokemonRepository
import com.example.pokedex.presentation.ui.pokemon_list.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetPokemonsNextPage (private val repository: IpokemonRepository) {

    suspend fun execute(page: Int) {
        withContext(Dispatchers.IO){
            val range = (page * PAGE_SIZE - PAGE_SIZE + 1)..(page  * PAGE_SIZE)
            val cachedPokemons = repository.getPokemonsInDb(range)
            if (cachedPokemons == null){
                val pokesFromNetwork =  repository.getPokemonsFromNetwork(offset = page * PAGE_SIZE - PAGE_SIZE)
                repository.insertPokemonsInDb(pokemonsToInsertInDb = pokesFromNetwork)
            }
        }
    }
}