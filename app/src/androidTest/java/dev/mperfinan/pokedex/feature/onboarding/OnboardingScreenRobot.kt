package dev.mperfinan.pokedex.feature.onboarding

import androidx.compose.ui.test.junit4.ComposeTestRule
import dev.mperfinan.pokedex.utility.robot.PokedexRobot

class OnboardingScreenRobot(composeTestRule: ComposeTestRule) : PokedexRobot(composeTestRule) {
    // -- Actions --

    fun clickNextButton() = clickByTag(NEXT_BUTTON_TEST_TAG)

    fun clickBackButton() = clickByTag(BACK_BUTTON_TEST_TAG)

    fun clickStartReadingButton() = clickByTag(START_EXPLORING_BUTTON_TEST_TAG)

    fun swipeNextOnPager() = swipeRightByTag(ONBOARDING_PAGER_TEST_TAG)

    fun swipeBackOnPager() = swipeLeftByTag(ONBOARDING_PAGER_TEST_TAG)

    // -- Assertions --

    fun verifyOnboardingItemDisplayed(
        expectedTitle: String,
        expectedDescription: String,
    ) {
        val expectedContentDescription = "$expectedTitle Image"
        assertWithText(expectedTitle)
        assertWithText(expectedDescription)
        assertWithContentDescription(expectedContentDescription)
    }

    fun verifyNextButtonDisplayed() = assertWithTag(NEXT_BUTTON_TEST_TAG)

    fun verifyBackButtonDisplayed() = assertWithTag(BACK_BUTTON_TEST_TAG)

    fun verifyStartReadingButtonDisplayed() = assertWithTag(START_EXPLORING_BUTTON_TEST_TAG)
}
