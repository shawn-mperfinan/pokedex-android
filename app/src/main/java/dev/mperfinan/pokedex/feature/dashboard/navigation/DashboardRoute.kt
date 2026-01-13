package dev.mperfinan.pokedex.feature.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.dashboard.DashboardScreenConnector
import dev.mperfinan.pokedex.utility.ValueChanged
import dev.mperfinan.pokedex.utility.VoidCallback
import kotlinx.serialization.Serializable

@Serializable
object DashboardRoute

fun NavGraphBuilder.dashboardScreen(
    onPokedexClick: VoidCallback,
    onItemsClick: VoidCallback,
    onMovesClick: VoidCallback,
    onTypesClick: VoidCallback,
    onFavoritesClick: VoidCallback,
    onSeeAllNewsClick: VoidCallback,
    onNewsCardClick: ValueChanged<Int>,
) {
    composable<DashboardRoute> {
        DashboardScreenConnector(
            onPokedexClick = onPokedexClick,
            onItemsClick = onItemsClick,
            onMovesClick = onMovesClick,
            onTypesClick = onTypesClick,
            onFavoritesClick = onFavoritesClick,
            onSeeAllNewsClick = onSeeAllNewsClick,
            onNewsCardClick = onNewsCardClick,
        )
    }
}
