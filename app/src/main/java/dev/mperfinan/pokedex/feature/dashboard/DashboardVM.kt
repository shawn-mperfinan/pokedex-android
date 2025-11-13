package dev.mperfinan.pokedex.feature.dashboard

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mperfinan.pokedex.data.repository.INewsRepository
import dev.mperfinan.pokedex.utility.manager.stateInWhileSubscribed
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DashboardVM @Inject constructor(
    newsRepository: INewsRepository,
) : ViewModel() {
    val uiState: StateFlow<DashboardUiState> =
        newsRepository.getPokemonNews(null, null).map {
            it.toDashboardUiState()
        }.stateInWhileSubscribed(DashboardUiState.Loading)
}
