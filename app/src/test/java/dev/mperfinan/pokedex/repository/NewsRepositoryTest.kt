package dev.mperfinan.pokedex.repository

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.repository.INewsRepository
import dev.mperfinan.pokedex.data.repository.NewsRepository
import dev.mperfinan.pokedex.data.source.remote.INewsNetworkDataSource
import dev.mperfinan.pokedex.data.source.remote.NewsNetworkDataSource
import dev.mperfinan.pokedex.fake.network.FakeNewsService
import dev.mperfinan.pokedex.utility.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class NewsRepositoryTest {
    private val testScheduler = TestCoroutineScheduler()
    private val testDispatcher = UnconfinedTestDispatcher(testScheduler)
    private lateinit var newsNetworkDataSource: INewsNetworkDataSource
    private lateinit var newsRepository: INewsRepository

    @BeforeEach
    fun setup() {
        newsNetworkDataSource = NewsNetworkDataSource(FakeNewsService())
        newsRepository = NewsRepository(newsNetworkDataSource, testDispatcher)
    }

    @Test
    fun `getPokemonNews should retrieve pokemon news from a successful assumed remote api request`() {
        runTest(testScheduler) {
            newsRepository.getPokemonNews(null, null).test {
                awaitItem()
                val result = awaitItem() as Result.Success

                assertThat(result).isInstanceOf(Result.Success::class.java)

                val pokemonNews = result.data

                assertThat(pokemonNews).isNotEmpty()
                assertThat(pokemonNews).hasSize(10)
                assertThat(pokemonNews.first().title).isEqualTo(
                    "Dynamax Eevee Debuts in Pokémon GO’s Dynamax Eevee Max Battle Weekend",
                )

                cancelAndConsumeRemainingEvents()
            }
        }
    }
}
