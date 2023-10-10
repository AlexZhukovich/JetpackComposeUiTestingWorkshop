package com.alexzh.moodtracker.presentation.component.loadingbutton

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.unit.dp
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

    /**
     * Verify the animation of the [LoadingButton] component with loading state.
     * Take screenshots at different time points after starting the test:
     * - 100 ms
     * - 200 ms
     * - 300 ms
     * - 400 ms
     */
    @Test
    fun loadingButton_loadingAnimation() {

    }
}