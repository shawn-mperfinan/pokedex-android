package dev.mperfinan.pokedex.feature.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.dashboard.DashboardScreen
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
    onPokemonNewsClick: VoidCallback,
) {
    composable<DashboardRoute> {
        DashboardScreen(
            onPokedexClick,
            onItemsClick,
            onMovesClick,
            onTypesClick,
            onFavoritesClick,
            onPokemonNewsClick,
        )
    }
}
