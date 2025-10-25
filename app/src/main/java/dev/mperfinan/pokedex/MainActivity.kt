package dev.mperfinan.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.mperfinan.pokedex.feature.dashboard.DashboardScreen
import dev.mperfinan.pokedex.feature.onboarding.OnboardingScreen
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val mainUiState = viewModel.mainUiState.collectAsStateWithLifecycle()
                if (mainUiState.value is MainUiState.Success) {
                    val appState = mainUiState.value as MainUiState.Success
                    val isAppFirstLaunch = appState.userPreferencesData.isAppFirstLaunch

                    if (isAppFirstLaunch) {
                        OnboardingScreen { viewModel.setAppLaunched(it) }
                    } else {
                        DashboardScreen()
                    }
                }
            }
        }
    }
}
