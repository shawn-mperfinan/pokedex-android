package dev.mperfinan.pokedex.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.MainUiState
import dev.mperfinan.pokedex.MainVM
import dev.mperfinan.pokedex.data.repository.IUserPreferencesRepository
import dev.mperfinan.pokedex.data.repository.UserPreferencesRepository
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import dev.mperfinan.pokedex.fake.datastore.FakeUserPreferencesDatastore
import dev.mperfinan.pokedex.utility.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(MainDispatcherRule::class)
class MainVMTest {
    private lateinit var userPreferencesDatastore: IUserPreferencesDatastore
    private lateinit var userPreferencesRepository: IUserPreferencesRepository
    private lateinit var viewModel: MainVM

    @BeforeEach
    fun setUp() {
        userPreferencesDatastore = FakeUserPreferencesDatastore()
        userPreferencesRepository = UserPreferencesRepository(userPreferencesDatastore)

        viewModel = MainVM(userPreferencesRepository = userPreferencesRepository)
    }

    @Test
    fun `mainUiState should retrieve (MainUiState_Success) state and collect (isAppFirstLaunch) default start value`() {
        runTest {
            viewModel.mainUiState.test {
                val mainUiState = awaitItem() as MainUiState.Success
                val isAppFirstLaunch = mainUiState.userPreferencesData.isAppFirstLaunch

                assertThat(isAppFirstLaunch).isTrue()

                cancelAndConsumeRemainingEvents()
            }
        }
    }

    @Test
    fun `mainUiState should retrieve (MainUiState_Success) state and collect (isAppFirstLaunch) new value`() {
        runTest {
            userPreferencesRepository.setIsAppFirstLaunch(false)

            viewModel.mainUiState.test {
                val mainUiState = awaitItem() as MainUiState.Success
                val isAppFirstLaunch = mainUiState.userPreferencesData.isAppFirstLaunch

                assertThat(isAppFirstLaunch).isFalse()

                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
