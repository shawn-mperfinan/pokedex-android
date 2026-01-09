package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.ui.core.composable.NewsCardLayout
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.utility.VoidCallback

@Composable
fun NewsCard(
    news: NewsArticle,
    onCardClick: VoidCallback,
) {
    NewsCardLayout(onClick = onCardClick) {
        AsyncImage(
            model = news.imageUrl,
            contentDescription = "News image content",
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize(),
        )
        // Gradient overlay for better text readability
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1f)),
                        ),
                    ),
        )
        // Text content
        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
        ) {
            Text(
                text = news.tag.uppercase(),
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Yellow),
            )
            VerticalSpace(4.dp)
            Text(
                text = news.title,
                style =
                    MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                    ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}
