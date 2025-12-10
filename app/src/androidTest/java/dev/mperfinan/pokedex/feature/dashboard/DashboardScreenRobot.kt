package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.ui.test.junit4.ComposeContentTestRule
import dev.mperfinan.pokedex.feature.dashboard.composable.FAVORITES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.ITEMS_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.MOVES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.POKEDEX_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.dashboard.composable.TYPES_BUTTON_TEST_TAG
import dev.mperfinan.pokedex.feature.favorites.FAVORITES_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.feature.items.ITEMS_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.feature.moves.MOVES_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.feature.news.POKEMON_NEWS_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.feature.pokedex.POKEDEX_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.feature.types.TYPES_SCREEN_TEST_TAG
import dev.mperfinan.pokedex.utility.robot.PokedexRobot

class DashboardScreenRobot(
    composeTestRule: ComposeContentTestRule,
    labels: Map<String, String>,
) : PokedexRobot(composeTestRule) {
    private val dashboardHeaderLabel = labels["DashboardHeaderLabel"]!!
    private val searchFieldPlaceHolderLabel = labels["SearchPlaceHolderLabel"]!!
    private val pokemonNewsLabel = labels["PokemonNewsLabel"]!!
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

    fun verifyRedirectedToPokedexScreen() = assertWithTag(POKEDEX_SCREEN_TEST_TAG)

    fun verifyRedirectedToItemsScreen() = assertWithTag(ITEMS_SCREEN_TEST_TAG)

    fun verifyRedirectedToMovesScreen() = assertWithTag(MOVES_SCREEN_TEST_TAG)

    fun verifyRedirectedToTypesScreen() = assertWithTag(TYPES_SCREEN_TEST_TAG)

    fun verifyRedirectedToFavoritesScreen() = assertWithTag(FAVORITES_SCREEN_TEST_TAG)

    fun verifyRedirectedToPokemonNewsScreen() = assertWithTag(POKEMON_NEWS_SCREEN_TEST_TAG)
}
