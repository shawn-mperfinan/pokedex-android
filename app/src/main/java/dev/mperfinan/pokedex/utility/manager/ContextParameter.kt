package dev.mperfinan.pokedex.utility.manager

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

/**
 * A recommended timeout value for WhileSubscribed in Android to smooth over configuration changes.
 * The upstream flow will stay active for this period after the last subscriber disappears.
 */
private const val STOP_TIMEOUT = 5_000L

/**
 * Creates a [StateFlow] in the [viewModelScope] that survives configuration changes.
 *
 * It uses a 5-second timeout via [SharingStarted.WhileSubscribed] to keep the
 * flow active after the last collector disappears.
 *
 * @param initialValue The initial value of the [StateFlow].
 */
context(viewModel: ViewModel)
fun <T> Flow<T>.stateInWhileSubscribed(initialValue: T): StateFlow<T> {
    return this.stateIn(
        scope = viewModel.viewModelScope,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT),
        initialValue = initialValue
    )
}
