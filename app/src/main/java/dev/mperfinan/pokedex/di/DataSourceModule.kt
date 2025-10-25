package dev.mperfinan.pokedex.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import dev.mperfinan.pokedex.data.source.datastore.UserPreferencesDatastore

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    fun bindUserPreferencesDataStore(userPreferencesDatastore: UserPreferencesDatastore): IUserPreferencesDatastore
}
