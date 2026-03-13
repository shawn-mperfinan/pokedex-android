package dev.mperfinan.pokedex.utility

import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.feature.about.navigation.AboutRoute
import dev.mperfinan.pokedex.feature.article.navigation.ArticleRoute
import dev.mperfinan.pokedex.feature.favorites.navigation.FavoritesRoute
import dev.mperfinan.pokedex.feature.feedback.navigation.FeedbackRoute
import dev.mperfinan.pokedex.feature.items.navigation.ItemsRoute
import dev.mperfinan.pokedex.feature.moves.navigation.MovesRoute
import dev.mperfinan.pokedex.feature.news.navigation.PokemonNewsRoute
import dev.mperfinan.pokedex.feature.pokedex.navigation.PokedexRoute
import dev.mperfinan.pokedex.feature.settings.navigation.SettingsRoute
import dev.mperfinan.pokedex.feature.types.navigation.TypesRoute

/**
 * Returns the top app bar title string based on the current route.
 * The title comes from localized string resources for easy translation.
 */
fun retrieveAppBarTitle(currentRoute: String?): Int {
    return when (currentRoute) {
        routeName<PokedexRoute>() -> R.string.title_pokedex
        routeName<ItemsRoute>() -> R.string.title_items
        routeName<MovesRoute>() -> R.string.title_moves
        routeName<TypesRoute>() -> R.string.title_types
        routeName<FavoritesRoute>() -> R.string.title_favorites
        routeName<PokemonNewsRoute>() -> R.string.title_pokemon_news
        routeName<SettingsRoute>() -> R.string.title_settings
        routeName<AboutRoute>() -> R.string.title_about
        routeName<FeedbackRoute>() -> R.string.title_feedback
        routeName<ArticleRoute>() -> R.string.title_article
        else -> R.string.app_name
    }
}

/**
 * Returns a route string representation for a typed navigation destination.
 *
 * Example:
 * routeName<PokedexRoute>() → "dev.mperfinan.pokedex.feature.pokedex.PokedexRoute"
 */
inline fun <reified T> routeName(): String {
    return T::class.qualifiedName ?: error("Route class must have a qualified name")
}
