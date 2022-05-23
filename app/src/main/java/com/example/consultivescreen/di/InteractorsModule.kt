package com.example.consultivescreen.di

import com.example.consultivescreen.cache.PokemonDao
import com.example.consultivescreen.cache.model.PokemonEntityMapper
import com.example.consultivescreen.data.IpokemonRepository
import com.example.consultivescreen.data.PokemonRepositoryImpl
import com.example.consultivescreen.domain.use_cases.pokemon_list.GetPokemonList
import com.example.consultivescreen.network.PokemonService
import com.example.consultivescreen.network.model.PokemonDetailDtoMapper
import com.example.consultivescreen.domain.use_cases.pokemon_detail.GetPokemonDetails
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