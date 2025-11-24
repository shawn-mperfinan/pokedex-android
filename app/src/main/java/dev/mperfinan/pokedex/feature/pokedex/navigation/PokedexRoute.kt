package dev.mperfinan.pokedex.feature.pokedex.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.pokedex.PokedexScreen
import kotlinx.serialization.Serializable

@Serializable
object PokedexRoute

fun NavController.navigateToPokedexScreen() {
    navigate(route = PokedexRoute)
}

fun NavGraphBuilder.pokedexScreen() {
    composable<PokedexRoute> {
        PokedexScreen()
    }
}
