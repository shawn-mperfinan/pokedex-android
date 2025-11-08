@file:Suppress("MagicNumber")

package dev.mperfinan.pokedex.ui.core.composable

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.utility.ValueChanged

/**
 * A stylized search field with a leading icon, rounded corners, and custom colors.
 *
 * @param searchInput The current search text value.
 * @param onValueChange The callback for text changes.
 * @param modifier The modifier to be applied to the search field.
 * @param placeHolder The placeholder text.
 */
@Composable
fun SearchField(
    searchInput: String,
    onValueChange: ValueChanged<String>,
    modifier: Modifier = Modifier,
    placeHolder: String = "",
) {
    OutlinedTextField(
        value = searchInput,
        onValueChange = onValueChange,
        placeholder = {
            Text(placeHolder)
        },
        leadingIcon = {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                // TODO: replace with proper color from colorScheme
                tint = Color(0xFF626262),
                contentDescription = "Search leading icon",
            )
        },
        modifier = modifier,
        shape = RoundedCornerShape(40),
        // TODO: replace with proper colors from colorScheme
        colors =
            OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color(0xFFE5E5E5),
                unfocusedContainerColor = Color(0xFFE5E5E5),
                focusedTextColor = Color(0xFF626262),
                unfocusedTextColor = Color(0xFF626262),
                focusedPlaceholderColor = Color(0xFF626262),
                unfocusedPlaceholderColor = Color(0xFF626262),
            ),
    )
}
