package dev.mperfinan.pokedex.viewmodel

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.repository.INewsRepository
import dev.mperfinan.pokedex.data.repository.NewsRepository
import dev.mperfinan.pokedex.data.source.remote.INewsNetworkDataSource
import dev.mperfinan.pokedex.data.source.remote.NewsNetworkDataSource
import dev.mperfinan.pokedex.fake.network.FakeNewsService
import dev.mperfinan.pokedex.feature.dashboard.DashboardUiState
import dev.mperfinan.pokedex.feature.dashboard.DashboardVM
import dev.mperfinan.pokedex.utility.MainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExperimentalCoroutinesApi
@ExtendWith(MainDispatcherRule::class)
class DashboardVMTest {
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(testScheduler)
    private lateinit var newsNetworkDataSource: INewsNetworkDataSource
    private lateinit var newsRepository: INewsRepository
    private lateinit var dashboardVM: DashboardVM

    @BeforeEach
    fun setup() {
        newsNetworkDataSource = NewsNetworkDataSource(FakeNewsService())
        newsRepository = NewsRepository(newsNetworkDataSource, testDispatcher)

        dashboardVM = DashboardVM(newsRepository)
    }

    @Test
    fun `uiState should emit DashboardUiState Success state when repository successfully supply pokemon news data`() {
        runTest(testScheduler) {
            dashboardVM.uiState.test {
                assertThat(awaitItem()).isEqualTo(DashboardUiState.Loading)

                val uiState = awaitItem() as DashboardUiState.Success
                val newsArticles = uiState.newsArticles

                assertThat(newsArticles).isNotEmpty()
                assertThat(newsArticles).hasSize(10)
                assertThat(newsArticles.first().title).isEqualTo(
                    "Dynamax Eevee Debuts in Pokémon GO’s Dynamax Eevee Max Battle Weekend",
                )

                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
