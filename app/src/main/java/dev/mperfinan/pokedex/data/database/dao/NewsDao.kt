package dev.mperfinan.pokedex.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.mperfinan.pokedex.data.database.entity.NewsEntity

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemonNews(news: List<NewsEntity>)

    @Query(value = "SELECT * FROM news ORDER BY id ASC")
    suspend fun getPokemonNews(): List<NewsEntity>

    @Query(value = "SELECT * FROM news WHERE id = :newsId")
    suspend fun getPokemonNewsArticle(newsId: Int): NewsEntity
}
