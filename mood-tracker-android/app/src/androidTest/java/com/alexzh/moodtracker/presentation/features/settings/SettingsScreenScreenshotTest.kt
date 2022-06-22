package com.alexzh.moodtracker.presentation.features.settings

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.component.LoadingButton
import com.alexzh.moodtracker.presentation.feature.settings.SettingsScreen
import com.alexzh.moodtracker.presentation.navigation.Screens
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.Orientation
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