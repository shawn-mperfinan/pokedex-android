package dev.mperfinan.pokedex.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.mperfinan.pokedex.core.model.NewsArticle

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "type")
    val type: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "short_description")
    val shortDescription: String,

    @ColumnInfo(name = "url")
    val url: String,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,

    @ColumnInfo(name = "date")
    val date: String,

    @ColumnInfo(name = "tag")
    val tag: String,
)

fun NewsEntity.asUiModel(): NewsArticle {
    return NewsArticle(
        id = id,
        type = type,
        title = title,
        shortDescription = shortDescription,
        url = url,
        imageUrl = imageUrl,
        date = date,
        tag = tag
    )
}
