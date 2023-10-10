package com.alexzh.moodtracker.presentation.component.calendar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.time.LocalDate

@ExperimentalMaterial3Api
@RunWith(JUnit4::class)
class WeekCalendarColorModeScreenshotTest : ScreenshotTest {
    private val testDate = LocalDate.of(2022, 5, 5)

    @get:Rule
    val composeTestRule = createEmptyComposeRule()

    /**
     * Verify the [WeekCalendar] component for a light mode
     * when the "todayDate" parameter is equal to "selectedDate" parameter.
     */
    @Test
    fun weekCalendar_light_todayDateIsEqualToSelectedDate() {

    }

    /**
     * Verify the [WeekCalendar] component for a dark mode
     * when the "todayDate" parameter is equal to "selectedDate" parameter.
     */
    @Test
    fun weekCalendar_dark_todayDateIsEqualToSelectedDate() {

    }

    /**
     * Verify the [WeekCalendar] component for a light mode
     * when the "todayDate" parameter is not equal to "selectedDate" parameter.
     */
    @Test
    fun weekCalendar_light_todayIsNotEqualToSelectedDate() {

    }

    /**
     * Verify the [WeekCalendar] component for a dark mode
     * when the "todayDate" parameter is not equal to "selectedDate" parameter.
     */
    @Test
    fun weekCalendar_dark_todayDateIsNotEqualToSelectedDate() {

    }
}