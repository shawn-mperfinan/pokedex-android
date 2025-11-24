package dev.mperfinan.pokedex.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable

@Serializable
object SettingsRoute

fun NavController.navigateToSettingsScreen() {
    navigate(route = SettingsRoute)
}

fun NavGraphBuilder.settingsScreen() {
    composable<SettingsRoute> {
        SettingsScreen()
    }
}
