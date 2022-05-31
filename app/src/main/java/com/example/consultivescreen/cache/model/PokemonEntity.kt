package com.example.consultivescreen.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemons")
data class PokemonEntity (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "typeOne")
    var typeOne: String,

    @ColumnInfo(name = "typeTwo")
    var typeTwo: String? = null,

    @ColumnInfo(name = "sprite")
    var pokemonSprite: String
)