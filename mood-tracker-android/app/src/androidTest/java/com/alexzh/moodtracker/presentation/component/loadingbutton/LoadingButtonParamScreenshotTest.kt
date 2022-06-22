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

    @Test
    fun loadingButton_customUiModeAndLoading(
            @TestParameter uiMode: UiMode,
            @TestParameter isLoading: Boolean
    ) {

    }
}