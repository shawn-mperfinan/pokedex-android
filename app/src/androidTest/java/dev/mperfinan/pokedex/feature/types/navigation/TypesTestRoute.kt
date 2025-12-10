package dev.mperfinan.pokedex.feature.types.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.types.TypesScreen
import kotlinx.serialization.Serializable

@Serializable
object TypesTestRoute

fun NavController.navigateToTypesTestScreen() {
    navigate(route = TypesTestRoute)
}

fun NavGraphBuilder.typesTestScreen() {
    composable<TypesTestRoute> {
        TypesScreen()
    }
}
