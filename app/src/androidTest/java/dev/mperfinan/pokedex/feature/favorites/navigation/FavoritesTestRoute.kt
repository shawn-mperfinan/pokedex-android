package dev.mperfinan.pokedex.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.favorites.FavoritesScreen
import kotlinx.serialization.Serializable

@Serializable
object FavoritesTestRoute

fun NavController.navigateToFavoritesTestScreen() {
    navigate(route = FavoritesTestRoute)
}

fun NavGraphBuilder.favoritesTestScreen() {
    composable<FavoritesTestRoute> {
        FavoritesScreen()
    }
}
