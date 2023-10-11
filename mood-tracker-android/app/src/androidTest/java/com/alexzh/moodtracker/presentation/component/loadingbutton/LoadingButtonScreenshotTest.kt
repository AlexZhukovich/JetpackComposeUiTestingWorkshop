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
class LoadingButtonScreenshotTest : ScreenshotTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @MediumTest
    @Test
    fun loadingButton_defaultState() {
        composableTestRule.apply {
            setContent {
                LoadingButton(
                    text = "Some text",
                    isLoading = false,
                    onClick = {},
                )
            }

            compareScreenshot(composableTestRule, "loadingButton_defaultState")
        }
    }

    @MediumTest
    @Test
    fun loadingButton_loadingState() {
        composableTestRule.apply {
            setContent {
                mainClock.autoAdvance = false
                LoadingButton(
                    text = "Some text",
                    isLoading = true,
                    onClick = {},
                )
            }

            mainClock.advanceTimeBy(400)
            compareScreenshot(composableTestRule, "loadingButton_loadingState")
        }
    }
}