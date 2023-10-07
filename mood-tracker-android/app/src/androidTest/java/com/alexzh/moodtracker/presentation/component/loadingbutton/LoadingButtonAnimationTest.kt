package com.alexzh.moodtracker.presentation.component.loadingbutton

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.component.LoadingButton
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoadingButtonAnimationTest : ScreenshotTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @MediumTest
    @Test
    fun loadingButton_loadingAnimation() {
        composableTestRule.apply {
            setContent {
                mainClock.autoAdvance = false
                LoadingButton(
                    text = "Some text",
                    isLoading = true,
                    onClick = {},
                )
            }

            compareScreenshot(composableTestRule, "loadingButton_loadingAnimation_state0")
            mainClock.advanceTimeBy(100)
            compareScreenshot(composableTestRule, "loadingButton_loadingAnimation_state1")
            mainClock.advanceTimeBy(200)
            compareScreenshot(composableTestRule, "loadingButton_loadingAnimation_state2")
            mainClock.advanceTimeBy(300)
            compareScreenshot(composableTestRule, "loadingButton_loadingAnimation_state3")
            mainClock.advanceTimeBy(400)
            compareScreenshot(composableTestRule, "loadingButton_loadingAnimation_state4")
        }
    }
}