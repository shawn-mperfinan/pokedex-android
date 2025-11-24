package dev.mperfinan.pokedex.data.repository

import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.utility.Result
import kotlinx.coroutines.flow.Flow

/**
 * Interface for a news repository to handle data operations retrieving news articles.
 */
interface INewsRepository {
    /**
     * Retrieves a paginated flow of general news articles.
     *
     * @param pageIndex The optional page index.
     * @param count The optional number of items per page.
     * @return A [Flow] emitting [Result] containing a [List] of [NewsArticle].
     */
    fun getPokemonNews(
        pageIndex: Int?,
        count: Int?,
    ): Flow<Result<List<NewsArticle>>>
}
