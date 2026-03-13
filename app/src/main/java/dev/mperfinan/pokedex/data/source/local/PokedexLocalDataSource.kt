package dev.mperfinan.pokedex.data.source.local

import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.data.database.dao.NewsDao
import dev.mperfinan.pokedex.data.database.dao.PokedexDao
import dev.mperfinan.pokedex.data.database.entity.NewsEntity
import dev.mperfinan.pokedex.data.database.entity.asUiModel
import dev.mperfinan.pokedex.data.network.model.news.NewsArticleDto
import dev.mperfinan.pokedex.data.network.model.news.asEntity
import javax.inject.Inject

class PokedexLocalDataSource @Inject constructor(
    private val newsDao: NewsDao,
    private val pokedexDao: PokedexDao,
) : IPokedexLocalDataSource {
    override suspend fun insertPokemonNews(news: List<NewsArticleDto>) {
        newsDao.insertPokemonNews(news.map(NewsArticleDto::asEntity))
    }

    override suspend fun getPokemonNews(): List<NewsArticle> {
        return newsDao.getPokemonNews().map(NewsEntity::asUiModel)
    }

    override suspend fun getNewsArticle(newsId: Int): NewsArticle {
        return newsDao.getPokemonNewsArticle(newsId).asUiModel()
    }
}
