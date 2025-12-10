package dev.mperfinan.pokedex.feature.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.dashboard.DashboardScreen
import dev.mperfinan.pokedex.feature.dashboard.DashboardUiState
import dev.mperfinan.pokedex.utility.VoidCallback
import kotlinx.serialization.Serializable

@Serializable
object DashboardTestRoute

fun NavGraphBuilder.dashboardTestScreen(
    onPokedexClick: VoidCallback,
    onItemsClick: VoidCallback,
    onMovesClick: VoidCallback,
    onTypesClick: VoidCallback,
    onFavoritesClick: VoidCallback,
    onSeeAllNewsClick: VoidCallback,
) {
    composable<DashboardTestRoute> {
        DashboardScreen(
            uiState = DashboardUiState.Loading,
            onPokedexClick = onPokedexClick,
            onItemsClick = onItemsClick,
            onMovesClick = onMovesClick,
            onTypesClick = onTypesClick,
            onFavoritesClick = onFavoritesClick,
            onSeeAllNewsClick = onSeeAllNewsClick,
        )
    }
}
