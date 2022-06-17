package com.example.pokedex.network.model

import com.example.pokedex.domain.model.PokemonDomain
import com.example.pokedex.domain.util.DomainMapper

class PokemonDtoMapper : DomainMapper<PokemonDto, PokemonDomain> {

    override fun mapToDomainModel(model: PokemonDto): PokemonDomain {
        return PokemonDomain(model.name, model.url)
    }

    //not used in this poc
    override fun mapFromDomainModel(domainModel: PokemonDomain): PokemonDto {
        return PokemonDto("","")
    }


}