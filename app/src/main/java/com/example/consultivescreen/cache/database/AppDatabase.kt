package com.example.consultivescreen.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.consultivescreen.cache.PokemonDao
import com.example.consultivescreen.cache.model.PokemonEntity

@Database(entities = [PokemonEntity::class ], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun pokemonDao(): PokemonDao

    companion object{
        val DATABASE_NAME: String = "pokemon_db"
    }


}