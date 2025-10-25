package dev.mperfinan.pokedex.ui.core

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "SmallPhoneLight",
    device = "spec:width=360dp,height=640dp,dpi=320",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFF5F5F7,
)
@Preview(
    name = "SmallPhoneDark",
    device = "spec:width=360dp,height=640dp,dpi=320",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF1F1D2B,
)
annotation class SmallPhonePreviews

@Preview(
    name = "MediumPhoneLight",
    device = "spec:width=411dp,height=891dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFF5F5F7,
)
@Preview(
    name = "MediumPhoneDark",
    device = "spec:width=411dp,height=891dp,dpi=420",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF1F1D2B,
)
annotation class MediumPhonePreviews

@Preview(
    name = "LargePhoneLight",
    device = "spec:width=480dp,height=1000dp,dpi=560",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true,
    backgroundColor = 0xFFF5F5F7,
)
@Preview(
    name = "LargePhoneDark",
    device = "spec:width=480dp,height=1000dp,dpi=560",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    backgroundColor = 0xFF1F1D2B,
)
annotation class LargePhonePreviews

@SmallPhonePreviews
@MediumPhonePreviews
@LargePhonePreviews
annotation class PhonePreviews
