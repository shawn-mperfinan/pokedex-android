package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.feature.dashboard.DashboardUiState
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding
import dev.mperfinan.pokedex.utility.ValueChanged
import dev.mperfinan.pokedex.utility.VoidCallback

private const val NEWS_SHIMMERING_COUNT = 5

@Composable
fun PokemonNews(
    listState: LazyListState,
    flingBehavior: FlingBehavior,
    uiState: DashboardUiState,
    onNewsCardClick: ValueChanged<Int>,
) {
    when (uiState) {
        is DashboardUiState.Loading -> {
            LazyRow(
                state = listState,
                flingBehavior = flingBehavior,
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(NEWS_SHIMMERING_COUNT) {
                    NewsCardShimmer()
                }
            }
        }

        is DashboardUiState.Error -> {
            // Display an error message
            Text(
                text = "Error: ${uiState.message}",
                color = Color.Red,
                modifier =
                    Modifier
                        .padding(horizontal = DefaultHorizontalPadding)
                        .fillMaxWidth(),
            )
        }

        is DashboardUiState.Success -> {
            LazyRow(
                state = listState,
                flingBehavior = flingBehavior,
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(uiState.newsArticles) { newsItem ->
                    NewsCard(
                        news = newsItem,
                        onCardClick = onNewsCardClick,
                    )
                }
            }
        }
    }
}
