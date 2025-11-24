package dev.mperfinan.pokedex

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.mperfinan.pokedex.feature.about.navigation.navigateToAboutScreen
import dev.mperfinan.pokedex.feature.dashboard.composable.PokedexScaffoldWithDrawer
import dev.mperfinan.pokedex.feature.dashboard.navigation.DashboardRoute
import dev.mperfinan.pokedex.feature.feedback.navigation.navigateToFeedbackScreen
import dev.mperfinan.pokedex.feature.settings.navigation.navigateToSettingsScreen
import dev.mperfinan.pokedex.navigation.MainNavHost
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import dev.mperfinan.pokedex.utility.retrieveAppBarTitle
import dev.mperfinan.pokedex.utility.routeName
import kotlinx.coroutines.launch

@Composable
fun PokedexApp(mainNavController: NavHostController = rememberNavController()) {
    val currentBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    val drawerScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    val appBarTitle = retrieveAppBarTitle(currentRoute)
    val isDashboardDestinationActive = currentRoute == routeName<DashboardRoute>()

    val itemSettings = stringResource(R.string.drawer_item_settings_label)
    val itemAbout = stringResource(R.string.drawer_item_about_label)
    val itemFeedback = stringResource(R.string.drawer_item_feedback_label)

    PokedexScaffoldWithDrawer(
        drawerState = drawerState,
        appBarTitle = appBarTitle,
        isDashboardDestinationActive = isDashboardDestinationActive,
        onMenuDrawerClicked = {
            drawerScope.launch { drawerState.open() }
        },
        onMenuItemClicked = { item ->
            drawerScope.launch { drawerState.close() }

            when (item) {
                itemSettings -> mainNavController.navigateToSettingsScreen()
                itemAbout -> mainNavController.navigateToAboutScreen()
                itemFeedback -> mainNavController.navigateToFeedbackScreen()
            }
        },
        onBackClicked = {
            mainNavController.navigateUp()
        },
    ) {
        MainNavHost(mainNavController)
    }
}

@PhonePreviews
@Composable
fun PokedexAppPreview() {
    PokedexTheme {
        PokedexApp()
    }
}
