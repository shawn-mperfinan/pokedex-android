package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding
import dev.mperfinan.pokedex.utility.VoidCallback

private const val DEFAULT_WIDTH_FRACTION = 0.74f
private const val DEFAULT_ASPECT_RATIO = 1.4f

@Composable
fun DashboardButton(
    label: String,
    gradientBackgroundColor: Brush,
    onClick: VoidCallback,
    testTag: String,
    modifier: Modifier = Modifier,
    imageWidthFraction: Float? = null,
    imageAspectRatio: Float? = null,
    horizontalPadding: Dp? = null,
) {
    Box(
        modifier =
            modifier
                .height(88.dp)
                .padding(horizontal = horizontalPadding ?: DefaultHorizontalPadding)
                .clip(RoundedCornerShape(16.dp))
                .background(brush = gradientBackgroundColor)
                .clickable(onClick = onClick)
                .testTag(testTag),
    ) {
        // Pokéball faint background
        Image(
            painter = painterResource(R.drawable.img_pokeball_button_bg_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxWidth(imageWidthFraction ?: DEFAULT_WIDTH_FRACTION)
                    .aspectRatio(imageAspectRatio ?: DEFAULT_ASPECT_RATIO),
        )

        // Label text
        Text(
            text = label,
            color = Color.White,
            style =
                MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    fontSize = 18.sp,
                ),
            modifier =
                Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 24.dp),
        )
    }
}

@Suppress("MagicNumber")
object DashboardGradientBrush {
    val POKEDEX =
        Brush.linearGradient(
            colors = listOf(Color(0xFF368E43), Color(0xFF5ABEA5)),
        )
    val ITEMS =
        Brush.linearGradient(
            colors = listOf(Color(0xFFA80003), Color(0xFFF26CA1)),
        )
    val MOVES =
        Brush.linearGradient(
            colors = listOf(Color(0xFF2A55A4), Color(0xFF59A9FC)),
        )
    val TYPES =
        Brush.linearGradient(
            colors = listOf(Color(0xFFEBA04B), Color(0xFFF6CC51)),
        )
    val FAVORITES =
        Brush.linearGradient(
            colors = listOf(Color(0xFF422F2F), Color(0xFFFFA98F)),
        )
}
