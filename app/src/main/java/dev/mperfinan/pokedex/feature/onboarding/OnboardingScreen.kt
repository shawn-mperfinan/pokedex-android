@file:Suppress("MagicNumber")

package dev.mperfinan.pokedex.feature.onboarding

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.core.PhonePreviews
import dev.mperfinan.pokedex.ui.theme.Pink40
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import dev.mperfinan.pokedex.ui.theme.Purple40
import dev.mperfinan.pokedex.ui.theme.Purple80
import dev.mperfinan.pokedex.ui.theme.PurpleGrey80
import kotlinx.coroutines.launch
import kotlin.math.absoluteValue

@Composable
fun OnboardingScreen(onStartExploring: (Boolean) -> Unit) {
    val onboardingScreenCounts = onboardingScreenEntries.size
    val onboardingNavigationScope = rememberCoroutineScope()
    val pagerState = rememberPagerState { onboardingScreenCounts }
    val currentPagerPage = pagerState.currentPage
    val isLastOnboardingScreen = currentPagerPage == onboardingScreenEntries.lastIndex
    val shouldShowBackButton = currentPagerPage > 0 && !isLastOnboardingScreen

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
    ) {
        HorizontalPager(
            state = pagerState,
            verticalAlignment = Alignment.Top,
            userScrollEnabled = !isLastOnboardingScreen,
            modifier =
                Modifier
                    .wrapContentSize(),
//                    .testTag() // uncomment this for UI testing
        ) { pagerPage ->
            val pagerOffset = (currentPagerPage - pagerPage) + pagerState.currentPageOffsetFraction

            OnboardingPagerContent(
                currentOnboardingScreen = onboardingScreenEntries[pagerPage],
                pagerOffset = pagerOffset.coerceIn(-1f, 1f),
            )
        }

        PageIndicators(
            pageCount = onboardingScreenCounts,
            currentPage = currentPagerPage,
            modifier = Modifier.padding(vertical = 40.dp),
        )

        if (isLastOnboardingScreen) {
            StartExploringButton(onStartExploring)
        } else {
            OnboardingNavigationButtons(
                shouldShowBackButton = shouldShowBackButton,
                onBackClick = {
                    onboardingNavigationScope.launch {
                        val previousPage = currentPagerPage - 1
                        pagerState.animateScrollToPage(page = previousPage)
                    }
                },
                onNextClick = {
                    onboardingNavigationScope.launch {
                        val nextPage = currentPagerPage + 1
                        pagerState.animateScrollToPage(page = nextPage)
                    }
                },
            )
        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
private fun OnboardingPagerContent(
    currentOnboardingScreen: OnboardingScreenItem,
    pagerOffset: Float,
) {
    val imageTopPadding = (LocalConfiguration.current.screenHeightDp.div(16)).dp
    val imageAlpha by animateFloatAsState(targetValue = 0.96f, label = "")
    val animationTween = remember { tween<Float>(durationMillis = 300) }
    val alpha by animateFloatAsState(
        targetValue = 1f - pagerOffset.absoluteValue,
        animationSpec = animationTween,
        label = "fade",
    )
    val scale by animateFloatAsState(
        targetValue = 1f - (0.1f * pagerOffset.absoluteValue),
        animationSpec = animationTween,
        label = "scale",
    )

    Column(
        modifier =
            Modifier
                .padding(horizontal = 24.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    this.scaleX = scale
                    this.scaleY = scale
                },
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // TODO - update aspect ratio for later support on multiple screen densities
        Image(
            modifier =
                Modifier
                    .aspectRatio(0.96f)
                    .fillMaxWidth()
                    .padding(top = imageTopPadding)
                    .alpha(imageAlpha),
            painter = painterResource(id = currentOnboardingScreen.media),
            contentDescription = stringResource(id = currentOnboardingScreen.title) + " Image",
        )

        Text(
            modifier = Modifier.padding(vertical = 34.dp),
            style = MaterialTheme.typography.titleLarge.copy(color = MaterialTheme.colorScheme.primary),
            textAlign = TextAlign.Center,
            text = stringResource(id = currentOnboardingScreen.title),
        )

        Text(
            style =
                MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f),
                ),
            textAlign = TextAlign.Center,
            text = stringResource(id = currentOnboardingScreen.description),
        )
    }
}

@Composable
private fun PageIndicators(
    pageCount: Int,
    currentPage: Int,
    modifier: Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        repeat(pageCount) { currentPageIndicated ->
            SingleDotIndicator(isSelected = currentPageIndicated == currentPage)
        }
    }
}

@Composable
private fun SingleDotIndicator(isSelected: Boolean) {
    val indicatorWidth by animateDpAsState(
        targetValue = if (isSelected) 20.dp else 8.dp,
        label = "",
    )
    val indicatorBackgroundColor = if (isSelected) Purple80 else Pink40

    Box(
        modifier =
            Modifier
                .padding(horizontal = 2.dp)
                .width(indicatorWidth)
                .height(8.dp)
                .clip(CircleShape)
                .background(indicatorBackgroundColor),
    )
}

@Composable
private fun StartExploringButton(onClickStartExploring: (Boolean) -> Unit) {
    Button(
        onClick = { onClickStartExploring(false) },
        colors = ButtonDefaults.buttonColors().copy(containerColor = Purple40),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp)
                .clip(RoundedCornerShape(20.dp)),
//                .testTag(START_READING_BUTTON_TEST_TAG),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.labelLarge.copy(color = PurpleGrey80),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.start_exploring_button_label),
        )
    }
}

@Composable
private fun OnboardingNavigationButtons(
    shouldShowBackButton: Boolean,
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 10.dp),
        horizontalArrangement = if (shouldShowBackButton) Arrangement.SpaceBetween else Arrangement.End,
    ) {
        if (shouldShowBackButton) {
            NavigationButton(
                buttonLabel = stringResource(id = R.string.back_button_label),
//                testTag = BACK_BUTTON_TEST_TAG,
                testTag = "",
                onClick = onBackClick,
            )
        }

        NavigationButton(
            buttonLabel = stringResource(id = R.string.next_button_label),
//            testTag = NEXT_BUTTON_TEST_TAG,
            testTag = "",
            fillColor = Purple40,
            onClick = onNextClick,
        )
    }
}

@Composable
private fun NavigationButton(
    buttonLabel: String,
    testTag: String,
    fillColor: Color = Color.Transparent,
    onClick: () -> Unit,
) {
    val textColor =
        if (fillColor == Color.Transparent) {
            MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        } else {
            PurpleGrey80
        }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(containerColor = fillColor),
        modifier =
            Modifier
                .width(86.dp)
                .clip(RoundedCornerShape(20.dp))
                .testTag(testTag),
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            style = MaterialTheme.typography.labelLarge.copy(color = textColor),
            textAlign = TextAlign.Center,
            text = buttonLabel,
        )
    }
}

@PhonePreviews
@Composable
fun OnboardingScreenPreview() {
    PokedexTheme {
        OnboardingScreen(onStartExploring = {})
    }
}
