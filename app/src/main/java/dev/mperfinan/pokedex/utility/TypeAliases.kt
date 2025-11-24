package dev.mperfinan.pokedex.utility

import androidx.compose.runtime.Composable

/**
 * A simple callback with no parameters and no return value.
 *
 * Commonly used for click actions, dismiss events, or triggers with no data payload.
 */
typealias VoidCallback = () -> Unit

/**
 * A generic callback that provides a new value of type [T].
 *
 * Typically used for text input, toggle changes, or any value-updating event.
 */
typealias ValueChanged<T> = (T) -> Unit

/**
 * Represents a composable lambda used to define UI content.
 *
 * Useful for parameters that accept composable content blocks (e.g., dialogs, containers).
 */
typealias UiContent = @Composable () -> Unit
