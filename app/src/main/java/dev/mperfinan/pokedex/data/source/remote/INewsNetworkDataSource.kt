package dev.mperfinan.pokedex.data.source.remote

import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto

/**
 * Interface for a network data source to fetch news-related data directly from a supposed API/MOCK API.
 */
interface INewsNetworkDataSource {
    /**
     * Retrieves a paginated list of news articles from the network.
     *
     * @param pageIndex The optional index of the page requested.
     * @param count The optional number of items per page.
     * @return An [ApiResult] which encapsulates the outcome.
     */
    suspend fun getPokemonNews(
        pageIndex: Int?,
        count: Int?,
    ): ApiResult<List<NewsArticleDto>>
}
