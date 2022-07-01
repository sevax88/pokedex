package com.example.pokedex.data

import android.util.Log
import android.util.Range
import com.example.pokedex.cache.PokemonDao
import com.example.pokedex.cache.model.PokemonEntityMapper
import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.network.PokemonService
import com.example.pokedex.network.model.PokemonDetailDto
import com.example.pokedex.network.model.PokemonDetailDtoMapper
import com.example.pokedex.network.model.PokemonsDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PokemonRepositoryImpl(
    private val pokemonService: PokemonService,
    private val pokemonDetailDtoMapper: PokemonDetailDtoMapper,
    private val pokemonDao: PokemonDao,
    private val pokemonEntityMapper: PokemonEntityMapper
) : IpokemonRepository {

    override suspend fun getPokemonsFromNetwork(offset: Int) : List<PokemonDetailDomain> {
        val pokemons : PokemonsDto = pokemonService.getPokemonsForPage(offset)

        val listOfDetailsPokesDto = mutableListOf<PokemonDetailDto>()

        for(poke in pokemons.listOfPokes){
            val urlParts = poke.url.split("/")
            val pokePosition = urlParts[urlParts.size -2].toInt()
            Log.d("repository", "calling for the details of a poke: $pokePosition")
            val pokeFullDetail = pokemonService.getPokemonDetails(id = pokePosition)
            listOfDetailsPokesDto.add(pokeFullDetail)
        }

        val listOfDetailedPokesDomain = mutableListOf<PokemonDetailDomain>()
        for(poke in listOfDetailsPokesDto){
            listOfDetailedPokesDomain.add(pokemonDetailDtoMapper.mapToDomainModel(poke))
        }

        return listOfDetailedPokesDomain
    }

    override suspend fun getAllPokemonsFromDb(): Flow<DataState<List<PokemonDetailDomain>>> {
        return pokemonDao.getAllPokemonsFlow().map {
            val list = mutableListOf<PokemonDetailDomain>()
            for (poke in it){
                list.add(pokemonEntityMapper.mapToDomainModel(poke))
            }
            DataState.success(list)
        }
    }

    override suspend fun insertPokemonsInDb(pokemonsToInsertInDb: List<PokemonDetailDomain>) {
        pokemonDao.insertPokemons(pokemons = pokemonEntityMapper.mapListDomainToListEntity(pokemonsToInsertInDb))
    }

    override suspend fun isDbEmpty(): Boolean {
        return pokemonDao.getFirstPokemon() == null
    }

    override suspend fun getPokemonsInDb(range: IntRange): List<PokemonDetailDomain>? {
        val pokesInDb = pokemonDao.getPokemonsInRange(range.first, range.last)
        return pokemonEntityMapper.mapListEntityToListDomain(pokesInDb)
    }

}