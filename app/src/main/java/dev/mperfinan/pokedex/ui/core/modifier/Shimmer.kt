package dev.mperfinan.pokedex.ui.core.modifier

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush

private const val SHIMMER_WIDTH = 300f

/**
 * Applies an animated shimmer effect used for loading or skeleton states.
 */
fun Modifier.shimmer(): Modifier =
    composed {
        val transition = rememberInfiniteTransition(label = "shimmer")

        val translateAnim by transition.animateFloat(
            initialValue = 0f,
            targetValue = 1000f,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = 1200,
                            easing = LinearEasing,
                        ),
                ),
            label = "shimmerTranslate",
        )

        val colorScheme = MaterialTheme.colorScheme
        val shimmerColors =
            listOf(
                colorScheme.surfaceVariant.copy(alpha = 0.6f),
                colorScheme.surfaceVariant.copy(alpha = 0.2f),
                colorScheme.surfaceVariant.copy(alpha = 0.6f),
            )

        background(
            brush =
                Brush.linearGradient(
                    colors = shimmerColors,
                    start = Offset(translateAnim - SHIMMER_WIDTH, 0f),
                    end = Offset(translateAnim, 0f),
                ),
        )
    }
