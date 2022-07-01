package com.example.pokedex.di

import com.example.pokedex.cache.PokemonDao
import com.example.pokedex.cache.model.PokemonEntityMapper
import com.example.pokedex.data.IpokemonRepository
import com.example.pokedex.data.PokemonRepositoryImpl
import com.example.pokedex.domain.use_cases.pokemon_list.GetPokemonsFirstPage
import com.example.pokedex.domain.use_cases.pokemon_list.GetPokemonsNextPage
import com.example.pokedex.network.PokemonService
import com.example.pokedex.network.model.PokemonDetailDtoMapper
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
    fun provideGetPokemonFirstPageUseCase(
        repository: IpokemonRepository
    ) : GetPokemonsFirstPage {
        return GetPokemonsFirstPage(repository)
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
    fun provideGetPokemonsNextPageUseCase(
        repository: IpokemonRepository
    ) : GetPokemonsNextPage {
        return GetPokemonsNextPage(repository)
    }

}