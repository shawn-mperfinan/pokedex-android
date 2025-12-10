package dev.mperfinan.pokedex.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.news.PokemonNewsScreen
import kotlinx.serialization.Serializable

@Serializable
object PokemonNewsTestRoute

fun NavController.navigateToPokemonNewsTestScreen() {
    navigate(route = PokemonNewsTestRoute)
}

fun NavGraphBuilder.pokemonNewsTestScreen() {
    composable<PokemonNewsTestRoute> {
        PokemonNewsScreen()
    }
}
