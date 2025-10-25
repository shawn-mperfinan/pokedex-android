package dev.mperfinan.pokedex.data.repository

import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserPreferencesRepository @Inject constructor(
    private val userPreferencesDataSource: IUserPreferencesDatastore,
) : IUserPreferencesRepository {
    override suspend fun setIsAppFirstLaunch(value: Boolean) {
        userPreferencesDataSource.setIsAppFirstLaunch(value)
    }

    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return userPreferencesDataSource.getIsAppFirstLaunch()
    }
}
