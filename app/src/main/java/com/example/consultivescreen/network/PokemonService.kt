package com.example.consultivescreen.network

import com.example.consultivescreen.network.model.PokemonDetailDto
import com.example.consultivescreen.network.model.PokemonsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonsList(
        @Query(value = "offset") offset : Int
    ): PokemonsDto

    @GET("pokemon/{id}")
    suspend fun getPokemonDetails(
        @Path(value = "id") id: Int
    ): PokemonDetailDto

}
