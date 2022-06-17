package com.example.consultivescreen.di

import com.example.consultivescreen.network.PokemonService
import com.example.consultivescreen.network.model.PokemonDetailDtoMapper
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providePokemonDetailMapper(): PokemonDetailDtoMapper = PokemonDetailDtoMapper()


    @Singleton
    @Provides
    fun providePokemonService(): PokemonService {

//        val interceptor = HttpLoggingInterceptor()
//        interceptor.level = HttpLoggingInterceptor.Level.BODY
//        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
//            .client(client)
            .build()
            .create(PokemonService::class.java)
    }
}