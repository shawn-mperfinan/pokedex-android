package dev.mperfinan.pokedex.ui.core.composable

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Adds a vertical [Spacer] of a specified height.
 *
 * This composable can be used to create consistent vertical gaps between other composables
 * within a [androidx.compose.foundation.layout.Column] or other vertical layouts.
 *
 * @param space The height of the vertical space.
 */
@Composable
fun VerticalSpace(space: Dp) {
    Spacer(modifier = Modifier.height(space))
}

/**
 * Adds a horizontal [Spacer] of a specified width.
 *
 * This composable is useful for creating consistent horizontal gaps between other composables
 * within a [androidx.compose.foundation.layout.Row] or other horizontal layouts.
 *
 * @param space The width of the horizontal space.
 */
@Composable
fun HorizonalSpace(space: Dp) {
    Spacer(modifier = Modifier.width(space))
}
