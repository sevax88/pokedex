package com.example.pokedex.di

import androidx.room.Room
import com.example.pokedex.cache.PokemonDao
import com.example.pokedex.cache.database.AppDatabase
import com.example.pokedex.cache.model.PokemonEntityMapper
import com.example.pokedex.presentation.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Singleton
    @Provides
    fun provideDb(app: BaseApplication): AppDatabase {
        return Room
            .databaseBuilder(app, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providePokemonDao(db: AppDatabase): PokemonDao{
        return db.pokemonDao()
    }

    @Singleton
    @Provides
    fun provideCacheRecipeMapper(): PokemonEntityMapper{
        return PokemonEntityMapper()
    }

}
