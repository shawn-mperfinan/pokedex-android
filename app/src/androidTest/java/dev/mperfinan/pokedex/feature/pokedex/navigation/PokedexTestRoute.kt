package dev.mperfinan.pokedex.feature.pokedex.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.pokedex.PokedexScreen
import kotlinx.serialization.Serializable

@Serializable
object PokedexTestRoute

fun NavController.navigateToPokedexTestScreen() {
    navigate(route = PokedexTestRoute)
}

fun NavGraphBuilder.pokedexTestScreen() {
    composable<PokedexTestRoute> {
        PokedexScreen()
    }
}
