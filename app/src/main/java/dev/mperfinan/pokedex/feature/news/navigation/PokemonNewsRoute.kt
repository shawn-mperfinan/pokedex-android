package dev.mperfinan.pokedex.feature.news.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.news.PokemonNewsScreen
import kotlinx.serialization.Serializable

@Serializable
object PokemonNewsRoute

fun NavController.navigateToPokemonNewsScreen() {
    navigate(route = PokemonNewsRoute)
}

fun NavGraphBuilder.pokemonNewsScreen() {
    composable<PokemonNewsRoute> {
        PokemonNewsScreen()
    }
}
