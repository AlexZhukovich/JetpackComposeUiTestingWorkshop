package com.alexzh.moodtracker.presentation.features.settings

import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.feature.settings.SettingsScreen
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.FontSize
import sergio.sastre.uitesting.utils.common.UiMode

@ExperimentalMaterial3Api
@RunWith(TestParameterInjector::class)
class SettingsScreenParamScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @MediumTest
    @Test
    fun settingsScreen_customUiMode(
        @TestParameter uiMode: UiMode
    ) {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(uiMode)
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
        compareScreenshot(composeTestRule, "settingsScreen_${uiMode}_defaultState")

        activityScenario.close()
    }


    @MediumTest
    @Test
    fun settingsScreen_customFontSize(@TestParameter fontSize: FontSize) {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setFontSize(fontSize)
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
        compareScreenshot(composeTestRule, "settingsScreen_${fontSize}_defaultState")

        activityScenario.close()
    }

    @MediumTest
    @Test
    fun settingsScreen_customFontSize(
        @TestParameter fontSize: FontSize,
        @TestParameter uiMode: UiMode
    ) {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setFontSize(fontSize)
            .setUiMode(uiMode)
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
        compareScreenshot(composeTestRule, "settingsScreen_${uiMode}_${fontSize}_defaultState")

        activityScenario.close()
    }
}