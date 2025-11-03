package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.drawer.PokedexDrawer
import dev.mperfinan.pokedex.ui.core.composable.PokemonContainer
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokedexScaffoldWithDrawer(
    drawerScope: CoroutineScope,
    drawerState: DrawerState,
    content: @Composable () -> Unit,
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            PokedexDrawer(
                onMenuItemClicked = { item ->
                    drawerScope.launch { drawerState.close() }
                    // TODO: add navigation to designated drawer item's path
                }
            )
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.app_name),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                            ),
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            drawerScope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
                )
            }
        ) { screenPadding ->
            PokemonContainer(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(screenPadding)
            ) {
                // Place custom scaffold content
                content()
            }
        }
    }
}

@PhonePreviews
@Composable
private fun PokedexScaffoldWithDrawerPreview() {
    PokedexTheme {
        PokedexScaffoldWithDrawer(
            drawerScope = rememberCoroutineScope(),
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
        ) { }
    }
}
