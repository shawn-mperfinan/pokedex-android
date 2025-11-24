package dev.mperfinan.pokedex.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import dev.mperfinan.pokedex.feature.about.navigation.aboutScreen
import dev.mperfinan.pokedex.feature.dashboard.navigation.DashboardRoute
import dev.mperfinan.pokedex.feature.dashboard.navigation.dashboardScreen
import dev.mperfinan.pokedex.feature.favorites.navigation.favoritesScreen
import dev.mperfinan.pokedex.feature.favorites.navigation.navigateToFavoritesScreen
import dev.mperfinan.pokedex.feature.feedback.navigation.feedbackScreen
import dev.mperfinan.pokedex.feature.items.navigation.itemsScreen
import dev.mperfinan.pokedex.feature.items.navigation.navigateToItemsScreen
import dev.mperfinan.pokedex.feature.moves.navigation.movesScreen
import dev.mperfinan.pokedex.feature.moves.navigation.navigateToMovesScreen
import dev.mperfinan.pokedex.feature.news.navigation.navigateToPokemonNewsScreen
import dev.mperfinan.pokedex.feature.news.navigation.pokemonNewsScreen
import dev.mperfinan.pokedex.feature.pokedex.navigation.navigateToPokedexScreen
import dev.mperfinan.pokedex.feature.pokedex.navigation.pokedexScreen
import dev.mperfinan.pokedex.feature.settings.navigation.settingsScreen
import dev.mperfinan.pokedex.feature.types.navigation.navigateToTypesScreen
import dev.mperfinan.pokedex.feature.types.navigation.typesScreen

@Composable
fun MainNavHost(mainNavController: NavHostController) {
    NavHost(
        navController = mainNavController,
        startDestination = DashboardRoute::class,
    ) {
        dashboardScreen(
            onPokedexClick = mainNavController::navigateToPokedexScreen,
            onItemsClick = mainNavController::navigateToItemsScreen,
            onMovesClick = mainNavController::navigateToMovesScreen,
            onTypesClick = mainNavController::navigateToTypesScreen,
            onFavoritesClick = mainNavController::navigateToFavoritesScreen,
            onSeeAllNewsClick = mainNavController::navigateToPokemonNewsScreen,
        )

        pokedexScreen()

        itemsScreen()

        movesScreen()

        typesScreen()

        favoritesScreen()

        pokemonNewsScreen()

        settingsScreen()

        aboutScreen()

        feedbackScreen()
    }
}
