package dev.mperfinan.pokedex.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.repository.IUserPreferencesRepository
import dev.mperfinan.pokedex.data.repository.UserPreferencesRepository
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import dev.mperfinan.pokedex.fake.datastore.FakeUserPreferencesDatastore
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class UserPreferencesRepositoryTest {
    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore

    private lateinit var userPreferencesRepository: IUserPreferencesRepository

    @BeforeEach
    fun setup() {
        userPreferencesDatastore = FakeUserPreferencesDatastore()

        userPreferencesRepository = UserPreferencesRepository(userPreferencesDatastore)
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve prefs default value as IS_APP_FIRST_LAUNCH is first initialized`() {
        testScope.runTest {
            userPreferencesRepository.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isTrue()
                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `getIsAppFirstLaunch should retrieve prefs new value when IS_APP_FIRST_LAUNCH assigns new one`() {
        testScope.runTest {
            userPreferencesRepository.setIsAppFirstLaunch(false)
            userPreferencesRepository.getIsAppFirstLaunch().test {
                val isAppFirstLaunch = awaitItem()
                assertThat(isAppFirstLaunch).isFalse()
                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
