package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.mperfinan.pokedex.ui.core.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme

@Composable
fun DashboardScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Text(
            text = "Hello Android!",
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@PhonePreviews
@Composable
fun DashboardScreenPreview() {
    PokedexTheme {
        DashboardScreen()
    }
}
