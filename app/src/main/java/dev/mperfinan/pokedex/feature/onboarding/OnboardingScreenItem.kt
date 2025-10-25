package dev.mperfinan.pokedex.feature.onboarding

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.mperfinan.pokedex.R

enum class OnboardingScreenItem(
    @param:DrawableRes val media: Int,
    @param:StringRes val title: Int,
    @param:StringRes val description: Int,
) {
    POKEMON_NEWS_UPDATES(
        media = R.drawable.img_pokemon_news_onboarding,
        title = R.string.pokemon_news_onboarding_label,
        description = R.string.pokemon_news_onboarding_description,
    ),

    EXPLORE_POKEDEX(
        media = R.drawable.img_pokedex_onboarding,
        title = R.string.pokedex_onboarding_label,
        description = R.string.pokedex_onboarding_description,
    ),

    SAVE_YOUR_FAVORITES(
        media = R.drawable.img_favorites_onboarding,
        title = R.string.favorites_onboarding_label,
        description = R.string.favorites_onboarding_description,
    ),
}

/**
 * List of onboarding screen features that can be utilized within the app
 */
val onboardingScreenEntries: List<OnboardingScreenItem> = OnboardingScreenItem.entries
