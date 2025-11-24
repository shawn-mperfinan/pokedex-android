package dev.mperfinan.pokedex.data.source.remote

import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto
import dev.mperfinan.pokedex.data.network.service.NewsApiService
import dev.mperfinan.pokedex.data.network.simulatedNetworkDelay
import javax.inject.Inject

/**
 * Concrete implementation of [INewsNetworkDataSource] that communicates with a backend API using [NewsApiService].
 *
 * @property newsApiService The Retrofit service interface for news API endpoints.
 */
class NewsNetworkDataSource @Inject constructor(
    private val newsApiService: NewsApiService,
) : INewsNetworkDataSource {
    /**
     * @see INewsNetworkDataSource.getPokemonNews
     */
    override suspend fun getPokemonNews(
        pageIndex: Int?,
        count: Int?,
    ): ApiResult<List<NewsArticleDto>> {
        simulatedNetworkDelay()

        return newsApiService.getPokemonNews(
            index = pageIndex ?: FIRST_PAGE_INDEX,
            count = count ?: PAGE_SIZE,
        )
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 0
        private const val PAGE_SIZE = 10
    }
}
