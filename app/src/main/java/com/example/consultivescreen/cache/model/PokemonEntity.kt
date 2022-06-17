package com.example.consultivescreen.cache.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.consultivescreen.domain.model.StatDomain

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
    var pokemonSprite: String,

    @ColumnInfo(name = "stat_attack")
    var statAttack: Int,

    @ColumnInfo(name = "stat_defense")
    var statDefense: Int,

    @ColumnInfo(name = "stat_hp")
    var statHp: Int,

    @ColumnInfo(name = "stat_sp_attack")
    var statSpecialAttack: Int,

    @ColumnInfo(name = "stat_sp_defense")
    var statSpecialDefense: Int,

    @ColumnInfo(name = "stat_speed")
    var statSpeed: Int,

)