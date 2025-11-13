package dev.mperfinan.pokedex.data.repository

import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto
import dev.mperfinan.pokedex.data.network.model.news.asUiModel
import dev.mperfinan.pokedex.data.network.onError
import dev.mperfinan.pokedex.data.network.onException
import dev.mperfinan.pokedex.data.network.onSuccess
import dev.mperfinan.pokedex.data.source.remote.INewsNetworkDataSource
import dev.mperfinan.pokedex.di.Dispatcher
import dev.mperfinan.pokedex.di.ScopeDispatcher
import dev.mperfinan.pokedex.utility.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

/**
 * Concrete implementation of [INewsRepository] that fetches news data from a
 * network data source ([INewsNetworkDataSource]).
 *
 * @property newsNetworkDataSource The data source responsible for network calls.
 * @property ioDispatcher The [CoroutineDispatcher] used for I/O operations.
 */
class NewsRepository @Inject constructor(
    private val newsNetworkDataSource: INewsNetworkDataSource,
    @param:Dispatcher(ScopeDispatcher.IO) private val ioDispatcher: CoroutineDispatcher,
) : INewsRepository {
    /**
     * @see INewsRepository.getPokemonNews
     */
    override fun getPokemonNews(
        pageIndex: Int?,
        count: Int?,
    ): Flow<Result<List<NewsArticle>>> {
        return flow {
            newsNetworkDataSource.getPokemonNews(pageIndex, count)
                .onSuccess { response ->
                    val pokemonNews = response.map(NewsArticleDto::asUiModel)
                    emit(Result.Success(pokemonNews))
                }
                .onError { code, message ->
                    emit(Result.Error(code, message))
                }
                .onException { throwable ->
                    emit(Result.Error(message = throwable.message ?: throwable.localizedMessage))
                }
        }.onStart {
            emit(Result.Loading)
        }.flowOn(ioDispatcher)
    }
}
