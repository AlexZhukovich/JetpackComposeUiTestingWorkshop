package com.alexzh.moodtracker.presentation.component.loadingbutton

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.unit.dp
import com.alexzh.moodtracker.presentation.component.LoadingButton
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.UiMode

@RunWith(JUnit4::class)
class LoadingButtonColorModeScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    /**
     * Verify the [LoadingButton] for a light mode with default state
     * - The "isLoading" property is equal to "false"
     *
     * Note:
     * - The "Modifier.background" property is show the difference between light and dark mode
     */
    @Test
    fun loadingButtonLight_light_defaultState() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.DAY)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    AppTheme {
                        Box(
                            modifier = Modifier.size(300.dp, height = 100.dp)
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            LoadingButton(
                                modifier = Modifier.width(250.dp),
                                text = "Test button",
                                isLoading = false,
                                onClick = { }
                            )
                        }
                    }
                }
            }
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "loadingButton_light_defaultState")

        activityScenario.close()
    }

    /**
     * Verify the [LoadingButton] for a dark mode with default state
     * - The "isLoading" property is equal to "false"
     */
    @Test
    fun loadingButtonLight_dark_defaultState() {

    }

    /**
     * Verify the [LoadingButton] for a light mode with default state
     * - The "isLoading" property is equal to "true"
     */
    @Test
    fun loadingButtonLight_light_loadingState() {

    }

    /**
     * Verify the [LoadingButton] for a dark mode with default state
     * - The "isLoading" property is equal to "true"
     */
    @Test
    fun loadingButtonLight_dark_loadingState() {

    }
}