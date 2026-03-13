package dev.mperfinan.pokedex.feature.article

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme

@Composable
fun ArticleScreen(
    viewModel: NewsArticleVM = hiltViewModel()
) {
    Text(
//        text = stringResource(R.string.title_article),
        text = "News ID: ${viewModel.newsId}",
        color = Color.White,
        style =
            MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp,
            ),
        modifier =
            Modifier
                .padding(start = 24.dp),
    )
}

@PhonePreviews
@Composable
fun ArticleScreenPreview() {
    PokedexTheme {
        ArticleScreen()
    }
}
