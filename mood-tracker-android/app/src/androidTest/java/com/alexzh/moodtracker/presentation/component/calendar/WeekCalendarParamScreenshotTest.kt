package com.alexzh.moodtracker.presentation.component.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import sergio.sastre.uitesting.utils.common.UiMode
import java.time.LocalDate

@ExperimentalMaterial3Api
@RunWith(TestParameterInjector::class)
class WeekCalendarParamScreenshotTest : ScreenshotTest {
    private val testDate = LocalDate.of(2022, 5, 5)

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    @Test
    fun weekCalendar_paramUiModeAndSelectedDate(
        @TestParameter uiMode: UiMode,
        @TestParameter todayIsSelectedDate: Boolean
    ) {

    }
}