package dev.mperfinan.pokedex.fake.datastore

import dev.mperfinan.pokedex.data.model.UserPreferencesData
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.update

@ExperimentalCoroutinesApi
class FakeUserPreferencesDatastore : IUserPreferencesDatastore {
    private val userPreferencesData =
        MutableStateFlow(
            UserPreferencesData(
                isAppFirstLaunch = true,
            ),
        )

    override suspend fun setIsAppFirstLaunch(value: Boolean) {
        userPreferencesData.update {
            it.copy(isAppFirstLaunch = value)
        }
    }

    override fun getIsAppFirstLaunch(): Flow<Boolean> {
        return userPreferencesData.mapLatest {
            it.isAppFirstLaunch
        }
    }
}
