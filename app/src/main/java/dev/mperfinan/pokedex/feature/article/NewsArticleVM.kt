package dev.mperfinan.pokedex.feature.article

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.navigation.toRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mperfinan.pokedex.feature.article.navigation.ArticleRoute
import javax.inject.Inject

@HiltViewModel
class NewsArticleVM @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val route = savedStateHandle.toRoute<ArticleRoute>()
    val newsId: Int = route.newsId // make this private later

    init {
        loadNewsArticle()
    }

    fun loadNewsArticle() {
        newsId
    }
}
