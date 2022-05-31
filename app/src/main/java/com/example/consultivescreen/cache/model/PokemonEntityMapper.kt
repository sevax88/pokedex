package com.example.consultivescreen.cache.model

import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.domain.util.DomainMapper

class PokemonEntityMapper : DomainMapper<PokemonEntity, PokemonDetailDomain> {

    override fun mapToDomainModel(model: PokemonEntity): PokemonDetailDomain {
        val types = mutableListOf<String>()
        types.add(model.typeOne)
        if (model.typeTwo != null){
            types.add(model.typeTwo!!)
        }
        return PokemonDetailDomain(
            image = model.pokemonSprite,
            name = model.name,
            types = types,
            id = model.id
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonDetailDomain): PokemonEntity {
//        var secondType: String? = null
//        secondType = try {
//            domainModel.types[1]
//        } catch (e: Exception) {
//            null
//        } finally {
//            return PokemonEntity(
//                typeOne = domainModel.types[0],
//                typeTwo = secondType,
//                name = domainModel.name,
//                pokemonSprite = domainModel.image,
//                id = domainModel.id
//            )
//        }
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
            id = domainModel.id
        )
    }
}