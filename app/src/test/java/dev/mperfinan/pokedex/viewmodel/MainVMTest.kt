package dev.mperfinan.pokedex.viewmodel

import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.MainUiState
import dev.mperfinan.pokedex.MainVM
import dev.mperfinan.pokedex.data.model.UserPreferencesData
import dev.mperfinan.pokedex.data.repository.IUserPreferencesRepository
import dev.mperfinan.pokedex.data.repository.UserPreferencesRepository
import dev.mperfinan.pokedex.data.source.datastore.IUserPreferencesDatastore
import dev.mperfinan.pokedex.fake.datastore.FakeUserPreferencesDatastore
import dev.mperfinan.pokedex.utility.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
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
    fun `mainUiState should retrieve (MainUiState_Loading) state value when first initialized`() {
        runTest {
            assertThat(viewModel.mainUiState.value).isEqualTo(MainUiState.Loading)
        }
    }

    @Test
    fun `mainUiState should retrieve (MainUiState_Success) state and collect (isAppFirstLaunch) default start value`() {
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.mainUiState.collect() }

            val isAppFirstLaunch = userPreferencesRepository.getIsAppFirstLaunch().first()
            val userPreferencesData = UserPreferencesData(isAppFirstLaunch = isAppFirstLaunch)

            assertThat(userPreferencesData.isAppFirstLaunch).isTrue()
            assertThat(viewModel.mainUiState.value).isEqualTo(MainUiState.Success(userPreferencesData))

            collectJob.cancel()
        }
    }

    @Test
    fun `mainUiState should retrieve (MainUiState_Success) state and collect (isAppFirstLaunch) new value`() {
        runTest {
            val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.mainUiState.collect() }

            userPreferencesRepository.setIsAppFirstLaunch(false)

            val isAppFirstLaunch = userPreferencesRepository.getIsAppFirstLaunch().first()
            val userPreferencesData = UserPreferencesData(isAppFirstLaunch = isAppFirstLaunch)

            assertThat(userPreferencesData.isAppFirstLaunch).isFalse()
            assertThat(viewModel.mainUiState.value).isEqualTo(MainUiState.Success(userPreferencesData))

            collectJob.cancel()
        }
    }
}
