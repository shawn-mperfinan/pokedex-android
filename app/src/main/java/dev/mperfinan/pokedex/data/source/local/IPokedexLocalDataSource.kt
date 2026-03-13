package dev.mperfinan.pokedex.data.source.local

import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto

interface IPokedexLocalDataSource {
    suspend fun insertPokemonNews(news: List<NewsArticleDto>)

    suspend fun getPokemonNews(): List<NewsArticle>

    suspend fun getNewsArticle(newsId: Int): NewsArticle
}
