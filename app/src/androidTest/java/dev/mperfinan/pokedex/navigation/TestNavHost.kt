package dev.mperfinan.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.mperfinan.pokedex.feature.dashboard.navigation.DashboardTestRoute
import dev.mperfinan.pokedex.feature.dashboard.navigation.dashboardTestScreen
import dev.mperfinan.pokedex.feature.favorites.navigation.favoritesTestScreen
import dev.mperfinan.pokedex.feature.favorites.navigation.navigateToFavoritesTestScreen
import dev.mperfinan.pokedex.feature.items.navigation.itemsTestScreen
import dev.mperfinan.pokedex.feature.items.navigation.navigateToItemsTestScreen
import dev.mperfinan.pokedex.feature.moves.navigation.movesTestScreen
import dev.mperfinan.pokedex.feature.moves.navigation.navigateToMovesTestScreen
import dev.mperfinan.pokedex.feature.news.navigation.navigateToPokemonNewsTestScreen
import dev.mperfinan.pokedex.feature.news.navigation.pokemonNewsTestScreen
import dev.mperfinan.pokedex.feature.pokedex.navigation.navigateToPokedexTestScreen
import dev.mperfinan.pokedex.feature.pokedex.navigation.pokedexTestScreen
import dev.mperfinan.pokedex.feature.types.navigation.navigateToTypesTestScreen
import dev.mperfinan.pokedex.feature.types.navigation.typesTestScreen

@Composable
fun TestNavHost(navHostController: NavHostController) {
    NavHost(
        navController = navHostController,
        startDestination = DashboardTestRoute::class,
    ) {
        dashboardTestScreen(
            onPokedexClick = navHostController::navigateToPokedexTestScreen,
            onItemsClick = navHostController::navigateToItemsTestScreen,
            onMovesClick = navHostController::navigateToMovesTestScreen,
            onTypesClick = navHostController::navigateToTypesTestScreen,
            onFavoritesClick = navHostController::navigateToFavoritesTestScreen,
            onSeeAllNewsClick = navHostController::navigateToPokemonNewsTestScreen,
        )

        pokedexTestScreen()

        itemsTestScreen()

        movesTestScreen()

        typesTestScreen()

        favoritesTestScreen()

        pokemonNewsTestScreen()
    }
}
