package dev.mperfinan.pokedex.network

import com.google.common.truth.Truth.assertThat
import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.onSuccess
import dev.mperfinan.pokedex.data.network.service.NewsApiService
import dev.mperfinan.pokedex.utility.PredefinedMockWebServer
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import tech.apter.junit.jupiter.robolectric.RobolectricExtension
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
@ExtendWith(RobolectricExtension::class)
class NewsServiceTest : PredefinedMockWebServer<NewsApiService>() {
    private lateinit var newsApiService: NewsApiService

    @BeforeEach
    fun setup() {
        newsApiService = createService(NewsApiService::class.java)
    }

    @Test
    fun `getPokemonNews should retrieve 200 response when request is valid`() {
        runTest {
            enqueueResponse("pokemon_news.json", HttpURLConnection.HTTP_OK)

            val result = newsApiService.getPokemonNews(0, 10)

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
