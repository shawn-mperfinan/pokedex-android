package dev.mperfinan.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mperfinan.pokedex.data.database.PokemonDatabase
import dev.mperfinan.pokedex.data.database.dao.NewsDao
import dev.mperfinan.pokedex.data.database.dao.PokedexDao

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {
    @Provides
    fun provideNewsDao(pokemonDatabase: PokemonDatabase): NewsDao {
        return pokemonDatabase.newsDao()
    }

    @Provides
    fun providePokedexDao(pokemonDatabase: PokemonDatabase): PokedexDao {
        return pokemonDatabase.pokedexDao()
    }
}
