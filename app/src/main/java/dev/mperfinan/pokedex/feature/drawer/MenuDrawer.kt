package dev.mperfinan.pokedex.feature.drawer

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Feedback
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.BuildConfig
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.composable.HorizonalSpace
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme

@Composable
fun PokedexDrawer(
    onMenuItemClicked: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth(fraction = 0.7f)
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .padding(24.dp)
    ) {
        DrawerHeader(
            label = stringResource(R.string.app_name),
            iconPainter = painterResource(R.drawable.img_pokeball_logo),
        )

        HorizontalDivider()

        VerticalSpace(20.dp)

        DrawerItem(
            label = stringResource(R.string.drawer_item_settings_label),
            icon = Icons.Default.Settings,
            onClick = onMenuItemClicked,
        )

        DrawerItem(
            label = stringResource(R.string.drawer_item_about_label),
            icon = Icons.Default.Info,
            onClick = onMenuItemClicked,
        )

        DrawerItem(
            label = stringResource(R.string.drawer_item_feedback_label),
            icon = Icons.Default.Feedback,
            onClick = onMenuItemClicked,
        )

        Spacer(modifier = Modifier.weight(1f))

        DrawerItem(
            label = stringResource(R.string.drawer_footer_label, BuildConfig.VERSION_NAME),
            icon = Icons.Default.Android,
            onClick = {},
        )
    }
}

@Composable
private fun DrawerHeader(
    label: String,
    iconPainter: Painter,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            painter = iconPainter,
            contentDescription = label,
            tint = Color.Unspecified
        )

        HorizonalSpace(16.dp)

        Text(
            text = label,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            ),
        )
    }
}

@Composable
private fun DrawerItem(
    label: String,
    icon: ImageVector,
    onClick: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(label) }
            .padding(vertical = 12.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.inverseSurface,
            modifier = Modifier.size(22.dp)
        )

        HorizonalSpace(16.dp)

        Text(
            text = label,
            color = MaterialTheme.colorScheme.inverseSurface,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
            ),
        )
    }
}

@PhonePreviews
@Composable
private fun PokedexDrawerPreview() {
    PokedexTheme {
        PokedexDrawer { }
    }
}
