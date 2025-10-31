package dev.mperfinan.pokedex.ui.core.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.compose.ui.res.painterResource
import dev.mperfinan.pokedex.R

/**
 * A container composable with a semi-transparent Pokeball logo in the background.
 *
 * This composable can hold other UI elements defined in its [content] lambda,
 * which will be placed on top of the stylized background.
 *
 * @param modifier The modifier to be applied to the container.
 * @param content The composable content to be displayed inside the container.
 */
@Composable
fun PokemonContainer(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val containerSize = LocalWindowInfo.current.containerSize
    val density = LocalDensity.current

    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.img_pokeball_bg_logo),
            contentDescription = "pokeball bg logo",
            modifier = Modifier
                // scales proportionally
                .fillMaxWidth(0.6f)
                .aspectRatio(1f)
                .align(Alignment.TopEnd)
                .graphicsLayer {
                    // Move up relative to total height, roughly 12% of screen height
                    val yOffsetPx = with(density) { -(containerSize.height.toDp() * 0.12f).toPx() }
                    translationY = yOffsetPx
                },
            contentScale = ContentScale.Crop,
        )

        // Place custom container content
        content()
    }
}
