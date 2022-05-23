package com.example.consultivescreen.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.consultivescreen.cache.model.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonEntity): Long

    @Query("SELECT * FROM pokemons WHERE id = :id")
    suspend fun getPokemonById(id: Int): PokemonEntity?

}