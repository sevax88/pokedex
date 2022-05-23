package com.example.consultivescreen.cache.model

import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.domain.util.DomainMapper

class PokemonEntityMapper : DomainMapper<PokemonEntity, PokemonDetailDomain> {

    override fun mapToDomainModel(model: PokemonEntity): PokemonDetailDomain {
        return PokemonDetailDomain(
            image = model.pokemonSprite,
            name = model.name,
            type = model.type,
            id = model.id
        )
    }

    override fun mapFromDomainModel(domainModel: PokemonDetailDomain): PokemonEntity {
        return PokemonEntity(
            type = domainModel.type,
            name = domainModel.name,
            pokemonSprite = domainModel.image,
            id = domainModel.id
        )
    }
}