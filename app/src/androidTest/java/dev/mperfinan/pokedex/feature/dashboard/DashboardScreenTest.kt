package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.navigation.TestNavHost
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DashboardScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navHostController: TestNavHostController

    private lateinit var dashboardRobot: DashboardScreenRobot

    private lateinit var labels: Map<String, String>

    @Before
    fun setupDashboardScreen() {
        composeTestRule.setContent {
            navHostController =
                TestNavHostController(LocalContext.current).apply {
                    navigatorProvider.addNavigator(ComposeNavigator())
                }

            TestNavHost(navHostController)

            labels =
                mapOf(
                    "DashboardHeaderLabel" to stringResource(R.string.dashboard_header_label),
                    "SearchPlaceHolderLabel" to stringResource(R.string.search_placeholder_label),
                    "PokemonNewsLabel" to stringResource(R.string.pokemon_news_section_label),
                    "ViewAll" to stringResource(dev.mperfinan.pokedex.R.string.view_all_news_button_label),
                )
        }

        dashboardRobot = DashboardScreenRobot(composeTestRule, labels)
    }

    // --- Screen Entry Test ---

    @Test
    fun `verify dashboard header items displayed upon screen entry`() {
        with(dashboardRobot) {
            verifyDashboardHeaderSectionDisplayed()
            verifyDashboardMainButtonsDisplayed()
            verifyPokemonNewsHeaderDisplayed()
        }
    }

    // --- Redirection Behavior Tests ---

    @Test
    fun `verify pokedex dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickPokedexDashboardButton()
            verifyRedirectedToPokedexScreen()
        }
    }

    @Test
    fun `verify items dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickItemsDashboardButton()
            verifyRedirectedToItemsScreen()
        }
    }

    @Test
    fun `verify moves dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickMovesDashboardButton()
            verifyRedirectedToMovesScreen()
        }
    }

    @Test
    fun `verify types dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickTypesDashboardButton()
            verifyRedirectedToTypesScreen()
        }
    }

    @Test
    fun `verify favorites dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickFavoritesDashboardButton()
            verifyRedirectedToFavoritesScreen()
        }
    }

    @Test
    fun `verify view all dashboard button is clicked then redirected to designated page`() {
        with(dashboardRobot) {
            clickViewAllDashboardButton()
            verifyRedirectedToPokemonNewsScreen()
        }
    }
}
