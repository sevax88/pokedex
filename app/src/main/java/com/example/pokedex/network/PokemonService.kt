package com.example.pokedex.network

import com.example.pokedex.network.model.PokemonDetailDto
import com.example.pokedex.network.model.PokemonsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonsForPage(
        @Query(value = "offset") offset : Int
    ): PokemonsDto

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path(value = "id") id: Int
    ): PokemonDetailDto

}
