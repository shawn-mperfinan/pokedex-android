package dev.mperfinan.pokedex.data.network.service

import dev.mperfinan.pokedex.data.network.ApiResult
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit interface defining the network endpoint for retrieving pokemon news data.
 */
interface NewsApiService {
    /**
     * Retrieves a paginated list of news articles from the specified endpoint.
     *
     * @param index The starting index or page number for pagination.
     * @param count The number of items to return per request.
     * @return An [ApiResult] wrapping a list of [NewsArticleDto] objects.
     */
    @GET("api/1/us/news/get-news.json")
    suspend fun getPokemonNews(
        @Query("index") index: Int,
        @Query("count") count: Int,
    ): ApiResult<List<NewsArticleDto>>
}
