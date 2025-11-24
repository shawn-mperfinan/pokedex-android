package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding
import dev.mperfinan.pokedex.utility.VoidCallback

@Composable
fun PokemonNewsHeader(onSeeAllNewsClick: VoidCallback) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHorizontalPadding, vertical = 26.dp),
        horizontalArrangement = Arrangement.Absolute.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.pokemon_news_section_label),
            style =
                MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                ),
        )

        TextButton(
            onClick = onSeeAllNewsClick,
            colors =
                ButtonDefaults.textButtonColors(
                    contentColor = MaterialTheme.colorScheme.tertiary,
                ),
            contentPadding = PaddingValues(0.dp),
        ) {
            Text(
                text = stringResource(R.string.view_all_news_button_label),
                style =
                    MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    ),
                textDecoration = TextDecoration.Underline,
            )
        }
    }
}
