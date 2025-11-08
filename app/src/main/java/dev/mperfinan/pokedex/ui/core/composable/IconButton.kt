package dev.mperfinan.pokedex.ui.core.composable

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import dev.mperfinan.pokedex.utility.VoidCallback

/**
 * A composable [IconButton] that displays a menu (hamburger) icon.
 *
 * @param onClicked Callback invoked when the menu icon is clicked.
 * @param contentDescription Optional description for accessibility services (e.g., TalkBack).
 * Defaults to `"Menu"`.
 */
@Composable
fun MenuDrawerIconButton(
    onClicked: VoidCallback,
    contentDescription: String? = "Menu",
) {
    IconButton(onClick = onClicked) {
        Icon(
            imageVector = Icons.Default.Menu,
            contentDescription = contentDescription,
        )
    }
}

/**
 * A composable [IconButton] that displays a back arrow icon.
 *
 * @param onClicked Callback invoked when the back arrow icon is clicked.
 * @param contentDescription Optional description for accessibility services (e.g., TalkBack).
 * Defaults to `"Back"`.
 */
@Composable
fun BackArrowIconButton(
    onClicked: VoidCallback,
    contentDescription: String? = "Back",
) {
    IconButton(onClick = onClicked) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = contentDescription,
        )
    }
}
