package com.example.pokedex.domain.use_cases.pokemon_detail

import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.network.PokemonService
import com.example.pokedex.network.model.PokemonDetailDtoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetPokemonDetails (
    private val pokemonService: PokemonService,
    private val pokemonDetailDtoMapper: PokemonDetailDtoMapper
){

    fun execute(index : Int) : Flow<DataState<PokemonDetailDomain>> = flow {

        try {
            val pokemonDetailDto = pokemonService.getPokemonDetails(index)
            val pokemonDetailDomain = pokemonDetailDtoMapper.mapToDomainModel(pokemonDetailDto)
            emit(DataState.success(pokemonDetailDomain))
        } catch (e: Exception) {
            print(e.message)
            emit(DataState.error<PokemonDetailDomain>("Something went wrong"))
        }
    }
}