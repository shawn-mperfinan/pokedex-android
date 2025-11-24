package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.feature.dashboard.DashboardUiState
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding

@Composable
fun PokemonNews(
    listState: LazyListState,
    flingBehavior: FlingBehavior,
    uiState: DashboardUiState,
) {
    when (uiState) {
        is DashboardUiState.Loading -> {
            // Display a loading indicator centered horizontally
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                // Define a height for the loading area
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator()
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
                    NewsCard(news = newsItem) {
                        // TODO: implement callback behavior
                    }
                }
            }
        }
    }
}
