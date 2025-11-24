package dev.mperfinan.pokedex.feature.types.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.types.TypesScreen
import kotlinx.serialization.Serializable

@Serializable
object TypesRoute

fun NavController.navigateToTypesScreen() {
    navigate(route = TypesRoute)
}

fun NavGraphBuilder.typesScreen() {
    composable<TypesRoute> {
        TypesScreen()
    }
}
