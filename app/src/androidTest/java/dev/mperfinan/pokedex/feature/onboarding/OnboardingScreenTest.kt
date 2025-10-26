package dev.mperfinan.pokedex.feature.onboarding

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.junit4.createComposeRule
import dev.mperfinan.pokedex.R
import dev.mperfinan.pokedex.ui.theme.PokedexTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OnboardingScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var onboardingRobot: OnboardingScreenRobot

    private lateinit var fakeOnboardingScreenItems: List<Pair<String, String>>

    private var startExploringClicked: Boolean = false

    @Before
    fun setupOnboardingScreen() {
        composeTestRule.setContent {
            val context = LocalContext.current

            fakeOnboardingScreenItems =
                listOf(
                    Pair(
                        context.getString(R.string.pokemon_news_onboarding_label),
                        context.getString(R.string.pokemon_news_onboarding_description),
                    ),
                    Pair(
                        context.getString(R.string.pokedex_onboarding_label),
                        context.getString(R.string.pokedex_onboarding_description),
                    ),
                    Pair(
                        context.getString(R.string.favorites_onboarding_label),
                        context.getString(R.string.favorites_onboarding_description),
                    ),
                )
            PokedexTheme {
                OnboardingScreen(onStartExploring = { startExploringClicked = true })
            }
        }

        onboardingRobot = OnboardingScreenRobot(composeTestRule)
    }

    // --- Screen Entry Test ---

    @Test
    fun `verify first item is showed upon initial onboarding screen entry`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.first()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    // --- Button Visibility Tests ---

    @Test
    fun `verify Next button is displayed when first onboarding item is shown`() {
        onboardingRobot.verifyNextButtonDisplayed()
    }

    @Test
    fun `verify Next and Back buttons are displayed when screen is swiped to the second onboarding item`() {
        with(onboardingRobot) {
            swipeNextOnPager()
            verifyNextButtonDisplayed()
            verifyBackButtonDisplayed()
        }
    }

    @Test
    fun `verify Start Exploring button is visible when screen is swiped to the last onboarding item`() {
        with(onboardingRobot) {
            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            verifyStartReadingButtonDisplayed()
        }
    }

    @Test
    fun `verify Start Exploring button is visible when Next button is clicked up to the last onboarding item`() {
        with(onboardingRobot) {
            repeat(fakeOnboardingScreenItems.lastIndex) {
                clickNextButton()
            }
            verifyStartReadingButtonDisplayed()
        }
    }

    // --- Swiping Behavior Tests ---

    @Test
    fun `verify second onboarding item is displayed when swiping right to left on the screen from the first item`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[1]

            swipeNextOnPager()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun `verify last onboarding item is displayed when swiped up to the last onboarding item`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun `verify viewpager scrolling is disabled when last onboarding item is displayed`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            repeat(fakeOnboardingScreenItems.lastIndex) {
                swipeNextOnPager()
            }
            swipeBackOnPager()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    // --- Button Behavior Tests ---

    @Test
    fun `verify second onboarding item is displayed when Next button is clicked from first onboarding item`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[1]

            clickNextButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun `verify first onboarding item is displayed when Back button is clicked from second onboarding item`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems[0]

            clickNextButton()
            clickBackButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun `verify last onboarding item is displayed when Next button is clicked from second onboarding item`() {
        with(onboardingRobot) {
            val (expectedTitle, expectedDescription) = fakeOnboardingScreenItems.last()

            clickNextButton()
            clickNextButton()
            verifyOnboardingItemDisplayed(expectedTitle, expectedDescription)
        }
    }

    @Test
    fun `verify onclick callback is triggered upon clicking Start Exploring button`() {
        with(onboardingRobot) {
            clickNextButton()
            clickNextButton()
            clickStartReadingButton()
            assert(startExploringClicked) // Check if button is really clicked
        }
    }
}
