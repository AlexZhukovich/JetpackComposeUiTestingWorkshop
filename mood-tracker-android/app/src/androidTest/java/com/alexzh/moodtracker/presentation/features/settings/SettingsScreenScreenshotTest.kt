package com.alexzh.moodtracker.presentation.features.settings

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.feature.settings.SettingsScreen
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.UiMode

@ExperimentalMaterial3Api
@RunWith(JUnit4::class)
class SettingsScreenScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @MediumTest
    @Test
    fun settingsScreen_defaultState() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.DAY)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    AppTheme {
                        SettingsScreen(
                            onProfile = {},
                            onDocs = {}
                        )
                    }
                }
            }

        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "settingsScreen_light_defaultState")

        activityScenario.close()
    }

    @MediumTest
    @Test
    fun settingsScreen_dark_defaultState() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.NIGHT)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    AppTheme {
                        SettingsScreen(
                            onProfile = {},
                            onDocs = {}
                        )
                    }
                }
            }

        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "settingsScreen_dark_defaultState")

        activityScenario.close()
    }
}