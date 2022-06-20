package com.example.pokedex.di

import com.example.pokedex.cache.PokemonDao
import com.example.pokedex.cache.model.PokemonEntityMapper
import com.example.pokedex.data.IpokemonRepository
import com.example.pokedex.data.PokemonRepositoryImpl
import com.example.pokedex.domain.use_cases.pokemon_list.GetPokemonList
import com.example.pokedex.network.PokemonService
import com.example.pokedex.network.model.PokemonDetailDtoMapper
import com.example.pokedex.domain.use_cases.pokemon_detail.GetPokemonDetails
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object InteractorsModule {

    @ViewModelScoped
    @Provides
    fun provideGetPokemonUseCase(
        repository: IpokemonRepository
//        service: PokemonService,
//        mapper: PokemonDetailDtoMapper
    ) : GetPokemonList {
        return GetPokemonList(repository)
    }

    @ViewModelScoped
    @Provides
    fun providePokemonRepository(
        service: PokemonService,
        mapper: PokemonDetailDtoMapper,
        entityMapper: PokemonEntityMapper,
        dao: PokemonDao
    ): IpokemonRepository {
        return PokemonRepositoryImpl(service, mapper, dao, entityMapper)
    }

    @ViewModelScoped
    @Provides
    fun provideGetPokemonDetailsUseCase(
        service: PokemonService,
        mapper: PokemonDetailDtoMapper
    ) : GetPokemonDetails {
        return GetPokemonDetails(service, mapper)
    }

}