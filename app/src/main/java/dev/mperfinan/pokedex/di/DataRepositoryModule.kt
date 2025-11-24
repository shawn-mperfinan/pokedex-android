package dev.mperfinan.pokedex.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mperfinan.pokedex.data.repository.INewsRepository
import dev.mperfinan.pokedex.data.repository.IUserPreferencesRepository
import dev.mperfinan.pokedex.data.repository.NewsRepository
import dev.mperfinan.pokedex.data.repository.UserPreferencesRepository

@Module
@InstallIn(SingletonComponent::class)
interface DataRepositoryModule {
    @Binds
    fun bindUserPreferencesRepository(userPreferencesRepository: UserPreferencesRepository): IUserPreferencesRepository

    @Binds
    fun bindNewsRepository(newsRepository: NewsRepository): INewsRepository
}
