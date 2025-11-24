package dev.mperfinan.pokedex.feature.items.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.items.ItemsScreen
import kotlinx.serialization.Serializable

@Serializable
object ItemsRoute

fun NavController.navigateToItemsScreen() {
    navigate(route = ItemsRoute)
}

fun NavGraphBuilder.itemsScreen() {
    composable<ItemsRoute> {
        ItemsScreen()
    }
}
