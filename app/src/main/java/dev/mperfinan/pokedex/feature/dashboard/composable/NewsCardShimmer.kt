package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.ui.core.composable.NewsCardLayout
import dev.mperfinan.pokedex.ui.core.composable.ShimmerLine
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.ui.core.modifier.shimmer

@Composable
fun NewsCardShimmer() {
    NewsCardLayout(onClick = {}) {
        // Image placeholder (this defines height)
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .shimmer(),
        )

        // Gradient overlay (same as real card)
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                        ),
                    ),
        )

        // News details placeholder
        Column(
            modifier =
                Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp),
        ) {
            ShimmerLine(width = 60.dp, height = 12.dp)

            VerticalSpace(6.dp)

            ShimmerLine(height = 14.dp)

            VerticalSpace(4.dp)

            ShimmerLine(widthFraction = 0.75f, height = 14.dp)
        }
    }
}
