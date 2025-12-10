package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.mperfinan.pokedex.feature.dashboard.composable.DashboardHeader
import dev.mperfinan.pokedex.feature.dashboard.composable.DashboardMainButtons
import dev.mperfinan.pokedex.feature.dashboard.composable.PokemonNews
import dev.mperfinan.pokedex.feature.dashboard.composable.PokemonNewsHeader
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import dev.mperfinan.pokedex.utility.VoidCallback

val DefaultHorizontalPadding = 18.dp
val CommonVerticalSpace = 20.dp

@Composable
fun DashboardScreenConnector(
    viewModel: DashboardVM = hiltViewModel(),
    onPokedexClick: VoidCallback,
    onItemsClick: VoidCallback,
    onMovesClick: VoidCallback,
    onTypesClick: VoidCallback,
    onFavoritesClick: VoidCallback,
    onSeeAllNewsClick: VoidCallback,
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    DashboardScreen(
        uiState = uiState,
        onPokedexClick = onPokedexClick,
        onItemsClick = onItemsClick,
        onMovesClick = onMovesClick,
        onTypesClick = onTypesClick,
        onFavoritesClick = onFavoritesClick,
        onSeeAllNewsClick = onSeeAllNewsClick,
    )
}

@Composable
fun DashboardScreen(
    uiState: DashboardUiState,
    onPokedexClick: VoidCallback,
    onItemsClick: VoidCallback,
    onMovesClick: VoidCallback,
    onTypesClick: VoidCallback,
    onFavoritesClick: VoidCallback,
    onSeeAllNewsClick: VoidCallback,
) {
    val scrollingState = rememberScrollState()
    val listState = rememberLazyListState()
    val flingBehavior = rememberSnapFlingBehavior(listState)

    Column(modifier = Modifier.verticalScroll(scrollingState)) {
        DashboardHeader()

        VerticalSpace(CommonVerticalSpace)

        DashboardMainButtons(
            onPokedexClick,
            onItemsClick,
            onMovesClick,
            onTypesClick,
            onFavoritesClick,
        )

        PokemonNewsHeader(onSeeAllNewsClick)

        PokemonNews(
            listState = listState,
            flingBehavior = flingBehavior,
            uiState = uiState,
        )

        VerticalSpace(CommonVerticalSpace)
    }
}

@PhonePreviews
@Composable
fun DashboardScreenPreview() {
    PokedexTheme {
        DashboardScreen(
            uiState = DashboardUiState.Loading,
            onPokedexClick = {},
            onItemsClick = {},
            onMovesClick = {},
            onTypesClick = {},
            onFavoritesClick = {},
            onSeeAllNewsClick = {},
        )
    }
}
