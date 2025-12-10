package dev.mperfinan.pokedex.feature.news

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme

const val POKEMON_NEWS_SCREEN_TEST_TAG = "PokemonNewsScreen"

@Composable
fun PokemonNewsScreen() {
    Text(
        text = stringResource(R.string.pokemon_news_section_label),
        color = Color.White,
        style =
            MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp,
            ),
        modifier =
            Modifier
                .padding(start = 24.dp)
                .testTag(POKEMON_NEWS_SCREEN_TEST_TAG),
    )
}

@PhonePreviews
@Composable
fun PokemonNewsScreenPreview() {
    PokedexTheme {
        PokemonNewsScreen()
    }
}
