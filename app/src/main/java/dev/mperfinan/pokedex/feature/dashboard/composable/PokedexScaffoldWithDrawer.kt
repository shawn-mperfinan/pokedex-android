package dev.mperfinan.pokedex.feature.dashboard.composable

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.composable.BackArrowIconButton
import dev.mperfinan.pokedex.ui.core.composable.MenuDrawerIconButton
import dev.mperfinan.pokedex.ui.core.composable.PokemonContainer
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import dev.mperfinan.pokedex.utility.UiContent
import dev.mperfinan.pokedex.utility.ValueChanged
import dev.mperfinan.pokedex.utility.VoidCallback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScaffoldWithDrawer(
    drawerState: DrawerState,
    appBarTitle: Int,
    isDashboardDestinationActive: Boolean,
    onMenuDrawerClicked: VoidCallback,
    onMenuItemClicked: ValueChanged<String>,
    onBackClicked: VoidCallback,
    content: UiContent,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokedexDrawer(onMenuItemClicked = onMenuItemClicked)
        },
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(appBarTitle),
                            style =
                                MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontFamily = FontFamily(Font(R.font.poppins_medium)),
                                ),
                        )
                    },
                    navigationIcon = {
                        if (isDashboardDestinationActive) {
                            MenuDrawerIconButton(onClicked = onMenuDrawerClicked)
                        } else {
                            BackArrowIconButton(onClicked = onBackClicked)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                )
            },
        ) { screenPadding ->
            PokemonContainer(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(screenPadding),
            ) {
                // Place custom scaffold content
                content()
            }
        }
    }
}

@Suppress("UnusedPrivateMember")
@PhonePreviews
@Composable
private fun PokedexScaffoldWithDrawerPreview() {
    PokedexTheme {
        PokedexScaffoldWithDrawer(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            appBarTitle = R.string.app_name,
            isDashboardDestinationActive = true,
            onMenuDrawerClicked = {},
            onMenuItemClicked = {},
            onBackClicked = {},
        ) { }
    }
}
