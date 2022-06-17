package com.example.pokedex.data

import android.util.Log
import com.example.pokedex.cache.PokemonDao
import com.example.pokedex.cache.model.PokemonEntityMapper
import com.example.pokedex.domain.data.DataState
import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.network.PokemonService
import com.example.pokedex.network.model.PokemonDetailDto
import com.example.pokedex.network.model.PokemonDetailDtoMapper
import com.example.pokedex.network.model.PokemonsDto
import com.example.pokedex.presentation.ui.pokemon_list.PAGE_SIZE

class PokemonRepositoryImpl(
    private val pokemonService: PokemonService,
    private val pokemonDetailDtoMapper: PokemonDetailDtoMapper,
    private val pokemonDao: PokemonDao,
    private val pokemonEntityMapper: PokemonEntityMapper
) : IpokemonRepository {

    override suspend fun getPokemonList(page: Int): DataState<List<PokemonDetailDomain>> {

        Log.d("repository", "calling for the page of a pokes: $page")

        /*
        Map from page to offset as the api works with offsets but from the view´s perspective is
        common to work with pages. The app will request poke info for pokes with ids starting from
        offset until offset + page size
         */

        var offset = 0
        var rangeToCheckFromCache = 0..0

        offset = if (page == 1) {
            0
        } else {
            page * PAGE_SIZE - PAGE_SIZE
        }

        rangeToCheckFromCache = if (page == 1){
            1..20
        } else {
            offset + 1..offset + PAGE_SIZE
        }

        Log.d("repository", "calling for the offset: $offset")

        /*First check the cache, if the cache does not contain this page then get the info from the
        network. This way we ensure a single source of truth, the cache.
         */

        /*
        Cache
         */
        val pokesRequestedAlreadyInCache = mutableListOf<PokemonDetailDomain>()
        for (i in rangeToCheckFromCache){
            Log.d("repository", "request from cache pokemon with id: $i")
            val pokeFromCache = pokemonDao.getPokemonById(i)
            if (pokeFromCache != null) {
                Log.d("repository", "success getting pokemon from cache pokemon with id: $i")
                pokesRequestedAlreadyInCache.add(pokemonEntityMapper.mapToDomainModel(pokeFromCache))
            }
        }

        Log.d("repository", "pokesRequestedAlreadyInCache.size = ${pokesRequestedAlreadyInCache.size}")

        /*
        If all the elements in the range are already in cache, then return them directly from cache,
        if not, go to take them from the network.
         */
        if (pokesRequestedAlreadyInCache.size == 20) {
            Log.d("repository", "getting the pokes from cache")
            return DataState.success(pokesRequestedAlreadyInCache)
        }

        /*
        Network
         */
        val pokemons : PokemonsDto = pokemonService.getPokemonsList(offset)

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

        //ToDO: add a method in the dao for addAll instead of add them individually.
        //Insert into the cache
        for (poke in listOfDetailedPokesDomain){
            pokemonDao.insertPokemon(pokemon = pokemonEntityMapper.mapFromDomainModel(poke))
        }

        Log.d("repository", "getting the pokes from network")
        return DataState.success(listOfDetailedPokesDomain)
    }

}