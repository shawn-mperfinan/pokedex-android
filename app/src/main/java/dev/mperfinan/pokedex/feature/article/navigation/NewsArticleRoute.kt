package dev.mperfinan.pokedex.feature.article.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.article.ArticleScreen
import kotlinx.serialization.Serializable

@Serializable
data class ArticleRoute(
    val newsId: Int,
)

fun NavController.navigateToArticleScreen(newsId: Int) {
    navigate(route = ArticleRoute(newsId))
}

fun NavGraphBuilder.articleScreen() {
    composable<ArticleRoute> {
        ArticleScreen()
    }
}

