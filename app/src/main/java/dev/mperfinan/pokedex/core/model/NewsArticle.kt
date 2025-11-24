package dev.mperfinan.pokedex.core.model

data class NewsArticle(
    val id: Int,
    val type: String,
    val title: String,
    val shortDescription: String,
    val url: String,
    val imageUrl: String,
    val date: String,
    val tag: String,
)
