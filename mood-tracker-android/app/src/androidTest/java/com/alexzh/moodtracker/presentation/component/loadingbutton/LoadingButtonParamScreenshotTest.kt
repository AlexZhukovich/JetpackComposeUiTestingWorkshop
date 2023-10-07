package com.alexzh.moodtracker.presentation.component.loadingbutton

import androidx.activity.compose.setContent
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.component.LoadingButton
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.UiMode

@RunWith(TestParameterInjector::class)
class LoadingButtonParamScreenshotTest : ScreenshotTest {

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    enum class Name(val text: String) {
        SHORT("short name"),
        LONG("super long super long super long  super long  super long super long super long")
    }

    @MediumTest
    @Test
    fun loadingButton_customUiModeAndLoading(
        @TestParameter uiMode: UiMode,
        @TestParameter isLoading: Boolean,
        @TestParameter name: Name
    ) {
        val loadingDescription = if (isLoading) "loading" else "default"


        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(uiMode)
            .launchConfiguredActivity()
            .onActivity {
                if (isLoading) {
                    composeTestRule.mainClock.autoAdvance = false
                }

                it.setContent {
                    LoadingButton(
                        text = name.text,
                        isLoading = isLoading,
                        onClick = {},
                    )
                }
            }

        if (isLoading) {
            composeTestRule.mainClock.advanceTimeBy(400)
        }
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "loadingButton_${uiMode}_${loadingDescription}_${name}")

        activityScenario.close()
    }
}