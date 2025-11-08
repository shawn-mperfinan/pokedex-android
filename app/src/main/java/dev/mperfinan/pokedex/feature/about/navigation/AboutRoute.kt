package dev.mperfinan.pokedex.feature.about.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.about.AboutScreen
import kotlinx.serialization.Serializable

@Serializable
object AboutRoute

fun NavController.navigateToAboutScreen() {
    navigate(route = AboutRoute)
}

fun NavGraphBuilder.aboutScreen() {
    composable<AboutRoute> {
        AboutScreen()
    }
}
