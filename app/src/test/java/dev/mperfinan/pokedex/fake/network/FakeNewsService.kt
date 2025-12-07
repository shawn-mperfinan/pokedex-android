package dev.mperfinan.pokedex.fake.network

import com.google.gson.Gson
import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto
import dev.mperfinan.pokedex.data.network.service.NewsApiService

class FakeNewsService : NewsApiService {
    private val newsData = loadNewsJson()

    override suspend fun getPokemonNews(
        index: Int,
        count: Int,
    ): ApiResult<List<NewsArticleDto>> {
        return ApiResult.Success(newsData)
    }

    private fun loadNewsJson(): List<NewsArticleDto> {
        val inputStream = javaClass.classLoader!!.getResourceAsStream("mock-response/pokemon_news.json")

        val json = inputStream.bufferedReader().use { it.readText() }
        return Gson().fromJson(json, Array<NewsArticleDto>::class.java).toList()
    }
}
