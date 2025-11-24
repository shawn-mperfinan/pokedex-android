package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import dev.mperfinan.pokedex.core.model.NewsArticle
import dev.mperfinan.pokedex.utility.VoidCallback

private const val BG_IMAGE_ASPECT_RATIO = 16 / 9f

@Composable
fun NewsCard(
    news: NewsArticle,
    onCardClick: VoidCallback,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .width(260.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                .clickable(onClick = onCardClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box {
            // Background image
            AsyncImage(
                model = news.imageUrl,
                contentDescription = "News image content",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(BG_IMAGE_ASPECT_RATIO),
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
                Spacer(modifier = Modifier.height(4.dp))
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
}
