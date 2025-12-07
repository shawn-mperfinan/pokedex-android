package dev.mperfinan.pokedex.network

import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.onSuccess
import dev.mperfinan.pokedex.data.source.remote.INewsNetworkDataSource
import dev.mperfinan.pokedex.data.source.remote.NewsNetworkDataSource
import dev.mperfinan.pokedex.fake.network.FakeNewsService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@ExperimentalCoroutinesApi
class NewsNetworkDataSourceTest {
    private lateinit var newsNetworkDataSource: INewsNetworkDataSource

    @BeforeEach
    fun setup() {
        newsNetworkDataSource = NewsNetworkDataSource(FakeNewsService())
    }

    @Test
    fun `getPokemonNews should return success with pokemon news data`() {
        runTest {
            val result = newsNetworkDataSource.getPokemonNews(0, 10)

            assertThat(result).isInstanceOf(ApiResult.Success::class.java)

            result.onSuccess { news ->
                assertThat(news).isNotEmpty()
                assertThat(news).hasSize(10)
                assertThat(news.first().title).isEqualTo(
                    "Dynamax Eevee Debuts in Pokémon&nbsp;GO’s Dynamax Eevee Max Battle Weekend",
                )
            }
        }
    }
}
