package dev.mperfinan.pokedex.feature.moves.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.moves.MovesScreen
import kotlinx.serialization.Serializable

@Serializable
object MovesTestRoute

fun NavController.navigateToMovesTestScreen() {
    navigate(route = MovesTestRoute)
}

fun NavGraphBuilder.movesTestScreen() {
    composable<MovesTestRoute> {
        MovesScreen()
    }
}
