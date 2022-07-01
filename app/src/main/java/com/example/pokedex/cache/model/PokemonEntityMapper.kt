package com.example.pokedex.cache.model

import com.example.pokedex.domain.model.PokemonDetailDomain
import com.example.pokedex.domain.model.StatDomain
import com.example.pokedex.domain.util.DomainMapper

class PokemonEntityMapper : DomainMapper<PokemonEntity, PokemonDetailDomain> {

    fun mapListDomainToListEntity(listOfDomain: List<PokemonDetailDomain>): List<PokemonEntity> {
        val listResult = mutableListOf<PokemonEntity>()
        for (pokemonDomain in listOfDomain) {
            listResult.add(mapFromDomainModel(pokemonDomain))
        }
        return listResult
    }

    fun mapListEntityToListDomain(listEntities: List<PokemonEntity>): List<PokemonDetailDomain>? {
        return if (listEntities.isEmpty()) null else {
            val listResult = mutableListOf<PokemonDetailDomain>()
            for (pokeEntity in listEntities){
                listResult.add(mapToDomainModel(pokeEntity))
            }
            listResult
        }
    }

    override fun mapToDomainModel(model: PokemonEntity): PokemonDetailDomain {

        //Types
        val types = mutableListOf<String>()
        types.add(model.typeOne)
        if (model.typeTwo != null){
            types.add(model.typeTwo!!)
        }

        //Stats
        val statDefense = StatDomain(name = "defense", model.statDefense)
        val statAttack = StatDomain(name = "attack", model.statAttack)
        val statHp = StatDomain(name = "hp", model.statHp)
        val statSpeed = StatDomain(name = "speed", model.statSpeed)
        val statSpDefense = StatDomain(name = "specialDefense", model.statSpecialDefense)
        val statSpAttack = StatDomain(name = "specialAttack", model.statSpecialAttack)
        val stats = listOf(statHp, statDefense, statAttack, statSpeed, statSpDefense, statSpAttack)

        return PokemonDetailDomain(
            image = model.pokemonSprite,
            name = model.name,
            types = types,
            id = model.id,
            listOfStats = stats,
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonDetailDomain): PokemonEntity {

        var typeTwo: String? = null
        if (domainModel.types.size > 1) {
            //means it has the two types
            typeTwo = domainModel.types[1]
        }

        return PokemonEntity(
            typeOne = domainModel.types[0],
            typeTwo = typeTwo,
            name = domainModel.name,
            pokemonSprite = domainModel.image,
            id = domainModel.id,
            statHp = domainModel.listOfStats[0].value,
            statAttack = domainModel.listOfStats[1].value,
            statDefense = domainModel.listOfStats[2].value,
            statSpecialAttack = domainModel.listOfStats[3].value,
            statSpecialDefense = domainModel.listOfStats[4].value,
            statSpeed = domainModel.listOfStats[5].value,
        )
    }
}