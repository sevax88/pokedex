package com.example.pokedex.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.cache.model.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemons(pokemons: List<PokemonEntity>): List<Long>

    @Query("SELECT * FROM pokemons WHERE id = :id")
    fun getPokemonById(id: Int): PokemonEntity?

    @Query("SELECT * FROM pokemons")
    fun getAllPokemonsFlow(): Flow<List<PokemonEntity>>

   @Query("SELECT * FROM pokemons WHERE id = 1")
   fun getFirstPokemon() : PokemonEntity?

    @Query("SELECT * FROM pokemons WHERE id BETWEEN :start AND :end")
    fun getPokemonsInRange(start: Int, end: Int) : List<PokemonEntity>

}