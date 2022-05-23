package com.example.consultivescreen.network.model

import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.domain.util.DomainMapper

class PokemonDetailDtoMapper : DomainMapper<PokemonDetailDto, PokemonDetailDomain> {

    override fun mapToDomainModel(model: PokemonDetailDto): PokemonDetailDomain {
        return PokemonDetailDomain(model.id, model.name, model.pokemonSpritesDto.frontDefault ?: "", model.typeDtos.first().type.name)
    }

    //I will not use this in this exercise, needed in case of the need to upload info to the network.
    override fun mapFromDomainModel(domainModel: PokemonDetailDomain): PokemonDetailDto {
        val type = PokemonTypeDto(type = PokemonTypeDetailInfoDto(name = domainModel.name, url = ""), slot = 0)
        return PokemonDetailDto(domainModel.id, domainModel.name, listOf(type), PokemonSpritesDto())
    }

}
