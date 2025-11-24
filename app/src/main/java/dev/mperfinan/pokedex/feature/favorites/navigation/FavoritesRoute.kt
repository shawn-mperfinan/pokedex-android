package dev.mperfinan.pokedex.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.favorites.FavoritesScreen
import kotlinx.serialization.Serializable

@Serializable
object FavoritesRoute

fun NavController.navigateToFavoritesScreen() {
    navigate(route = FavoritesRoute)
}

fun NavGraphBuilder.favoritesScreen() {
    composable<FavoritesRoute> {
        FavoritesScreen()
    }
}
