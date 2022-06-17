package com.example.consultivescreen.network.model

import com.example.consultivescreen.domain.model.PokemonDetailDomain
import com.example.consultivescreen.domain.model.StatDomain
import com.example.consultivescreen.domain.util.DomainMapper

class PokemonDetailDtoMapper : DomainMapper<PokemonDetailDto, PokemonDetailDomain> {

    override fun mapToDomainModel(model: PokemonDetailDto): PokemonDetailDomain {
        val types = mutableListOf<String>()
        for (type in model.typeDtos){
            types.add(type.type.name)
        }

        val stats = mutableListOf<StatDomain>()
        for (stat in model.listOfStats){
            stats.add(StatDomain(name = stat.statDetail.statName, value = stat.baseStat))
        }
        return PokemonDetailDomain(
            model.id,
            model.name,
            model.pokemonSpritesDto.frontDefault ?: "", types,
            listOfStats = stats,
            //not filling out the specie section in the mapper, the use case will fill this out later
        )
    }

    //I will not use this in this exercise, needed in case of the need to upload info to the network,
    // so no pay attention to the code.
    override fun mapFromDomainModel(domainModel: PokemonDetailDomain): PokemonDetailDto {
        val type = PokemonTypeDto(type = PokemonTypeDetailInfoDto(name = domainModel.name, url = ""), slot = 0)
        return PokemonDetailDto(
            domainModel.id,
            domainModel.name,
            listOf(type),
            PokemonSpritesDto(),
            listOfStats = emptyList(),
            specieDto = null,
        )
    }

}
