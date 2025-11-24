package dev.mperfinan.pokedex.feature.dashboard

import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.utility.Result

sealed interface DashboardUiState {
    object Loading : DashboardUiState

    data class Success(val newsArticles: List<NewsArticle>) : DashboardUiState

    data class Error(val message: String) : DashboardUiState
}

fun Result<List<NewsArticle>>.toDashboardUiState(): DashboardUiState {
    return when (this) {
        is Result.Loading -> DashboardUiState.Loading
        is Result.Success -> DashboardUiState.Success(data)
        is Result.Error -> DashboardUiState.Error(message.orEmpty())
    }
}
