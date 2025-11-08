package dev.mperfinan.pokedex.feature.feedback.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import dev.mperfinan.pokedex.feature.feedback.FeedbackScreen
import kotlinx.serialization.Serializable

@Serializable
object FeedbackRoute

fun NavController.navigateToFeedbackScreen() {
    navigate(route = FeedbackRoute)
}

fun NavGraphBuilder.feedbackScreen() {
    composable<FeedbackRoute> {
        FeedbackScreen()
    }
}
