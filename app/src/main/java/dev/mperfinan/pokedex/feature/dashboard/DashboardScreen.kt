package dev.mperfinan.pokedex.feature.dashboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.composable.PokemonContainer
import dev.mperfinan.pokedex.ui.core.composable.SearchField
import dev.mperfinan.pokedex.ui.core.composable.VerticalSpace
import dev.mperfinan.pokedex.ui.core.preview.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.PokedexTheme

private val news = listOf(
    PokemonNews(
        id = 1,
        category = "Trading Card Game",
        title = "Mega Dragonite Reveal Has Fans Uber Excited for Pokémon Legends",
        imageRes = R.drawable.img_pokemon_news_1
    ),
    PokemonNews(
        id = 2,
        category = "Gaming",
        title = "Pokémon TCG Pocket: Wisdom of Sea and Sky Expansion Revealed",
        imageRes = R.drawable.img_pokemon_news_2
    ),
    PokemonNews(
        id = 3,
        category = "Event",
        title = "Pokémon GO Safari Zone Announced for Hoenn Region",
        imageRes = R.drawable.img_pokemon_news_3
    ),
    PokemonNews(
        id = 4,
        category = "General",
        title = "Celebrate the Pokémon TCG: Mega Lucario ex Figure Collection with Pokémon GO",
        imageRes = R.drawable.img_pokemon_news_4
    ),
    PokemonNews(
        id = 5,
        category = "Video Games and Apps",
        title = "Costumed Pokémon Take Over the Pokémon GO Halloween 2025 Part II Event\n",
        imageRes = R.drawable.img_pokemon_news_5
    )
)

private val DefaultHorizontalPadding = 18.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Pokédex",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily(Font(R.font.poppins_medium)),
                        ),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent),
            )
        }
    ) { screenPadding ->
        PokemonContainer(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(screenPadding)
        ) {


            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
            ) {
                Text(
                    modifier = Modifier.padding(horizontal = DefaultHorizontalPadding, vertical = 20.dp),
                    text = "What Pokémon are you looking for?",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.inverseSurface,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    )
                )

                SearchField(
                    searchInput = "",
                    onValueChange = {},
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .padding(horizontal = DefaultHorizontalPadding),
                    // TODO: place this in a proper xml string property
                    placeHolder = "Search a pokémon",
                )

                VerticalSpace(20.dp)

                DashboardButton(
                    label = "Pokédex",
                    gradientBackgroundColor = Brush.linearGradient(
                        colors = listOf(Color(0xFF368E43), Color(0xFF5ABEA5))
                    ),
                    onClick = { },
                    modifier = Modifier.fillMaxWidth(),
                    imageWidthFraction = 0.6f,
                    imageAspectRatio = 1.8f,
                )

                VerticalSpace(8.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DefaultHorizontalPadding),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DashboardButton(
                        label = "Items",
                        gradientBackgroundColor = Brush.linearGradient(
                            colors = listOf(Color(0xFFA80003), Color(0xFFF26CA1))
                        ),
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        horizontalPadding = 0.dp,
                    )
                    DashboardButton(
                        label = "Moves",
                        gradientBackgroundColor = Brush.linearGradient(
                            colors = listOf(Color(0xFF2A55A4), Color(0xFF59A9FC))
                        ),
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        horizontalPadding = 0.dp,
                    )
                }

                VerticalSpace(8.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = DefaultHorizontalPadding),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    DashboardButton(
                        label = "Types",
                        gradientBackgroundColor = Brush.linearGradient(
                            colors = listOf(Color(0xFFEBA04B), Color(0xFFF6CC51))
                        ),
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        horizontalPadding = 0.dp,
                    )
                    DashboardButton(
                        label = "Favorites",
                        gradientBackgroundColor = Brush.linearGradient(
                            colors = listOf(Color(0xFF422F2F), Color(0xFFFFA98F))
                        ),
                        onClick = { },
                        modifier = Modifier.weight(1f),
                        horizontalPadding = 0.dp,
                    )
                }

                Text(
                    modifier = Modifier.padding(horizontal = DefaultHorizontalPadding, vertical = 26.dp),
                    text = "Pokemon News",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily(Font(R.font.poppins_medium)),
                    ),
                )

                val listState = rememberLazyListState()
                val flingBehavior = rememberSnapFlingBehavior(listState)

                PokemonNews(
                    listState = listState,
                    flingBehavior = flingBehavior,
                    news = news
                )

                VerticalSpace(20.dp)
            }
        }
    }
}

@Composable
private fun PokemonNews(
    listState: LazyListState,
    flingBehavior: FlingBehavior,
    news: List<PokemonNews>,
) {
    LazyRow(
        state = listState,
        flingBehavior = flingBehavior,
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(news) { newsItem ->
            NewsCard(news = newsItem) {
                // TODO: implement callback behavior
            }
        }
    }
}

@Composable
fun NewsCard(
    news: PokemonNews,
    onCardClick: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(260.dp)
            .shadow(elevation = 6.dp, shape = RoundedCornerShape(16.dp))
            .clickable(onClick = onCardClick),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box {
            // Background image
            Image(
                painter = painterResource(id = news.imageRes),
                contentDescription = "Pokéball background",
                contentScale = ContentScale.Crop,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .aspectRatio(16 / 9f),
            )
            // Gradient overlay for better text readability
            Box(
                modifier =
                    Modifier
                        .matchParentSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Color.Transparent, Color.Black.copy(alpha = 1f)),
                            )
                        )
            )
            // Text content
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(12.dp)
            ) {
                Text(
                    text = news.category.uppercase(),
                    style = MaterialTheme.typography.labelSmall.copy(color = Color.Yellow)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = news.title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
fun DashboardButton(
    label: String,
    gradientBackgroundColor: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageWidthFraction: Float? = null,
    imageAspectRatio: Float? = null,
    horizontalPadding: Dp? = null,
) {
    Box(
        modifier = modifier
            .height(88.dp)
            .padding(horizontal = horizontalPadding ?: DefaultHorizontalPadding)
            .clip(RoundedCornerShape(16.dp))
            .background(brush = gradientBackgroundColor)
            .clickable(onClick = onClick)
    ) {
        // Pokéball faint background
        Image(
            painter = painterResource(R.drawable.img_pokeball_button_bg_logo),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .fillMaxWidth(imageWidthFraction ?: 0.74f)
                .aspectRatio(imageAspectRatio ?: 1.4f)
        )

        // Label text
        Text(
            text = label,
            color = Color.White,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily(Font(R.font.poppins_medium)),
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .align(Alignment.CenterStart)
                .padding(start = 24.dp)
        )
    }
}

data class PokemonNews(
    val id: Int,
    val category: String,
    val title: String,
    val imageRes: Int,
)

@PhonePreviews
@Composable
fun DashboardScreenPreview() {
    PokedexTheme {
        DashboardScreen()
    }
}
