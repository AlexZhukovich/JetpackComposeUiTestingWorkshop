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
class LoadingButtonScreenshotTest : ScreenshotTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    /**
     * Verify the [LoadingButton] with a default state
     * - The "isLoading" property is equal to "false"
     */
    @MediumTest
    @Test
    fun loadingButton_defaultState() {

    }

    /**
     * Verify the [LoadingButton] with a loading state
     * - The "isLoading" property is equal to "true"
     */
    @MediumTest
    @Test
    fun loadingButton_loadingState() {

    }
}