package dev.mperfinan.pokedex.ui.core.composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.utility.VoidCallback

private const val BOX_IMAGE_ASPECT_RATIO = 16 / 9f

@Composable
fun NewsCardLayout(
    onClick: VoidCallback,
    content: @Composable BoxScope.() -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .width(260.dp)
                .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
                .clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .aspectRatio(BOX_IMAGE_ASPECT_RATIO),
            content = content,
        )
    }
}
