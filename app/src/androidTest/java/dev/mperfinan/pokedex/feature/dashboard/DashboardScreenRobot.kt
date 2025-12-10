package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import dev.mperfinan.pokedex.feature.dashboard.composable.FAVORITES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.ITEMS_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.MOVES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.POKEDEX_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.TYPES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.utility.robot.PokedexRobot

class DashboardScreenRobot(
    composeTestRule: ComposeContentTestRule,
    private val labels: Map<String, String>,
) : PokedexRobot(composeTestRule) {
    //    private val dashboardHeaderLabel by composeTestRule.stringResource(R.string.dashboard_header_label)
    private val dashboardHeaderLabel = labels["DashboardHeaderLabel"]!!

    //    private val searchFieldPlaceHolderLabel by composeTestRule.stringResource(R.string.search_placeholder_label)
    private val searchFieldPlaceHolderLabel = labels["SearchPlaceHolderLabel"]!!

    //    private val pokemonNewsLabel by composeTestRule.stringResource(R.string.pokemon_news_section_label)
    private val pokemonNewsLabel = labels["PokemonNewsLabel"]!!

    //    private val viewAllLabel by composeTestRule.stringResource(R.string.view_all_news_button_label)
    private val viewAllLabel = labels["ViewAll"]!!

    // -- Actions --

    fun clickPokedexDashboardButton() = clickByTag(POKEDEX_BUTTON_TEST_TAG)

    fun clickItemsDashboardButton() = clickByTag(ITEMS_BUTTON_TEST_TAG)

    fun clickMovesDashboardButton() = clickByTag(MOVES_BUTTON_TEST_TAG)

    fun clickTypesDashboardButton() = clickByTag(TYPES_BUTTON_TEST_TAG)

    fun clickFavoritesDashboardButton() = clickByTag(FAVORITES_BUTTON_TEST_TAG)

    fun clickViewAllDashboardButton() = clickByText(viewAllLabel)

    // -- Assertions --

    fun verifyDashboardHeaderSectionDisplayed() {
        assertWithText(dashboardHeaderLabel)
        assertWithText(searchFieldPlaceHolderLabel)
    }

    fun verifyDashboardMainButtonsDisplayed() {
        assertWithTag(POKEDEX_BUTTON_TEST_TAG)
        assertWithTag(ITEMS_BUTTON_TEST_TAG)
        assertWithTag(MOVES_BUTTON_TEST_TAG)
        assertWithTag(TYPES_BUTTON_TEST_TAG)
        assertWithTag(FAVORITES_BUTTON_TEST_TAG)
    }

    fun verifyPokemonNewsHeaderDisplayed() {
        assertWithText(pokemonNewsLabel)
        assertWithText(viewAllLabel)
    }
}
