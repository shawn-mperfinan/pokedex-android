package dev.mperfinan.pokedex.data.network.model.news

import dev.mperfinan.pokedex.core.model.NewsArticle

data class NewsArticleDto(
    val id: Int,
    val type: String,
    val title: String,
    val shortDescription: String,
    val url: String,
    val image: String,
    val alt: String,
    val date: String,
    val tags: List<NewsTags>,
)

fun NewsArticleDto.asUiModel(): NewsArticle {
    return NewsArticle(
        id = id,
        type = type,
        title = alt,
        shortDescription = shortDescription,
        url = url,
        imageUrl = "https://www.pokemon.com$image",
        date = date,
        tag = tags.first().label,
    )
}
