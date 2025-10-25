@file:Suppress("MagicNumber")

package dev.mperfinan.pokedex

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mperfinan.pokedex.data.model.UserPreferencesData
import dev.mperfinan.pokedex.data.repository.IUserPreferencesRepository
import dev.mperfinan.pokedex.utility.manager.stateInWhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val userPreferencesRepository: IUserPreferencesRepository,
) : ViewModel() {
    val mainUiState: StateFlow<MainUiState> =
        userPreferencesRepository.getIsAppFirstLaunch().map {
            MainUiState.Success(
                UserPreferencesData(isAppFirstLaunch = it),
            )
        }.stateInWhileSubscribed(initialValue = MainUiState.Loading)

    fun setAppLaunched(value: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setIsAppFirstLaunch(value)
        }
    }
}

sealed interface MainUiState {
    data object Loading : MainUiState

    data class Success(val userPreferencesData: UserPreferencesData) : MainUiState
}
