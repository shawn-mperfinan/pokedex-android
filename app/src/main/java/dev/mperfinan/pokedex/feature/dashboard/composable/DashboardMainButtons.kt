package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.utility.VoidCallback

const val POKEDEX_BUTTON_TEST_TAG = "PokedexMainButton"
const val ITEMS_BUTTON_TEST_TAG = "ItemsMainButton"
const val MOVES_BUTTON_TEST_TAG = "MovesMainButton"
const val TYPES_BUTTON_TEST_TAG = "TypesMainButton"
const val FAVORITES_BUTTON_TEST_TAG = "FavoritesMainButton"
private val ZeroPadding = 0.dp
private val CommonSpace = 8.dp

@Composable
fun DashboardMainButtons(
    onPokedexClick: VoidCallback,
    onItemsClick: VoidCallback,
    onMovesClick: VoidCallback,
    onTypesClick: VoidCallback,
    onFavoritesClick: VoidCallback,
) {
    DashboardButton(
        label = stringResource(R.string.pokedex_button_label),
        gradientBackgroundColor = DashboardGradientBrush.POKEDEX,
        onClick = onPokedexClick,
        testTag = POKEDEX_BUTTON_TEST_TAG,
        modifier = Modifier.fillMaxWidth(),
        imageWidthFraction = 0.6f,
        imageAspectRatio = 1.8f,
    )

    VerticalSpace(CommonSpace)

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(CommonSpace),
    ) {
        DashboardButton(
            label = stringResource(R.string.items_button_label),
            gradientBackgroundColor = DashboardGradientBrush.ITEMS,
            onClick = onItemsClick,
            testTag = ITEMS_BUTTON_TEST_TAG,
            modifier = Modifier.weight(1f),
            horizontalPadding = ZeroPadding,
        )
        DashboardButton(
            label = stringResource(R.string.moves_button_label),
            gradientBackgroundColor = DashboardGradientBrush.MOVES,
            onClick = onMovesClick,
            testTag = MOVES_BUTTON_TEST_TAG,
            modifier = Modifier.weight(1f),
            horizontalPadding = ZeroPadding,
        )
    }

    VerticalSpace(CommonSpace)

    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHorizontalPadding),
        horizontalArrangement = Arrangement.spacedBy(CommonSpace),
    ) {
        DashboardButton(
            label = stringResource(R.string.types_button_label),
            gradientBackgroundColor = DashboardGradientBrush.TYPES,
            onClick = onTypesClick,
            testTag = TYPES_BUTTON_TEST_TAG,
            modifier = Modifier.weight(1f),
            horizontalPadding = ZeroPadding,
        )
        DashboardButton(
            label = stringResource(R.string.favorites_button_label),
            gradientBackgroundColor = DashboardGradientBrush.FAVORITES,
            onClick = onFavoritesClick,
            testTag = FAVORITES_BUTTON_TEST_TAG,
            modifier = Modifier.weight(1f),
            horizontalPadding = ZeroPadding,
        )
    }
}
