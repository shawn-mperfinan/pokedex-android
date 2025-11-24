package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.dashboard.CommonVerticalSpace
import dev.mperfinan.pokedex.feature.dashboard.DefaultHorizontalPadding
import dev.mperfinan.pokedex.ui.core.composable.SearchField

@Composable
fun DashboardHeader() {
    Text(
        modifier =
            Modifier.padding(
                horizontal = DefaultHorizontalPadding,
                vertical = CommonVerticalSpace,
            ),
        text = stringResource(R.string.dashboard_header_label),
        style =
            MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.inverseSurface,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            ),
    )

    SearchField(
        searchInput = "",
        onValueChange = {},
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHorizontalPadding),
        placeHolder = stringResource(R.string.search_placeholder_label),
    )
}
