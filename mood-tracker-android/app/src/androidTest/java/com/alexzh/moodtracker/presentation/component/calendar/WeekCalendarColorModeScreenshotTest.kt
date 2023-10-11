package com.alexzh.moodtracker.presentation.component.calendar

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.unit.dp
import androidx.test.filters.MediumTest
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.karumi.shot.ActivityScenarioUtils.waitForActivity
import com.karumi.shot.ScreenshotTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import sergio.sastre.uitesting.utils.activityscenario.ActivityScenarioConfigurator
import sergio.sastre.uitesting.utils.common.UiMode
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
    @MediumTest
    @Test
    fun weekCalendar_light_todayDateIsEqualToSelectedDate() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.DAY)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    composeTestRule.mainClock.autoAdvance = false
                    AppTheme {
                        Box(
                            modifier = Modifier.size(400.dp, height = 200.dp)
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            WeekCalendar(
                                startDate = testDate.minusDays(6),
                                selectedDate = testDate.minusDays(1),
                                onSelectedDateChange = {},
                                todayDate = testDate.minusDays(1)
                            )
                        }
                    }
                }
            }

        composeTestRule.mainClock.advanceTimeBy(400)
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "weekCalendar_light_todayDateIsEqualToSelectedDate")

        activityScenario.close()
    }

    /**
     * Verify the [WeekCalendar] component for a dark mode
     * when the "todayDate" parameter is equal to "selectedDate" parameter.
     */
    @MediumTest
    @Test
    fun weekCalendar_dark_todayDateIsEqualToSelectedDate() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.NIGHT)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    composeTestRule.mainClock.autoAdvance = false
                    AppTheme {
                        Box(
                            modifier = Modifier.size(400.dp, height = 200.dp)
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            WeekCalendar(
                                startDate = testDate.minusDays(6),
                                selectedDate = testDate.minusDays(1),
                                onSelectedDateChange = {},
                                todayDate = testDate.minusDays(1)
                            )
                        }
                    }
                }
            }

        composeTestRule.mainClock.advanceTimeBy(400)
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "weekCalendar_dark_todayDateIsEqualToSelectedDate")

        activityScenario.close()
    }

    /**
     * Verify the [WeekCalendar] component for a light mode
     * when the "todayDate" parameter is not equal to "selectedDate" parameter.
     */
    @MediumTest
    @Test
    fun weekCalendar_light_todayIsNotEqualToSelectedDate() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.DAY)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    composeTestRule.mainClock.autoAdvance = false
                    AppTheme {
                        Box(
                            modifier = Modifier.size(400.dp, height = 200.dp)
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            WeekCalendar(
                                startDate = testDate.minusDays(6),
                                selectedDate = testDate.minusDays(1),
                                onSelectedDateChange = {},
                                todayDate = testDate.minusDays(2)
                            )
                        }
                    }
                }
            }

        composeTestRule.mainClock.advanceTimeBy(400)
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "weekCalendar_light_todayIsNotEqualToSelectedDate")

        activityScenario.close()
    }

    /**
     * Verify the [WeekCalendar] component for a dark mode
     * when the "todayDate" parameter is not equal to "selectedDate" parameter.
     */
    @MediumTest
    @Test
    fun weekCalendar_dark_todayDateIsNotEqualToSelectedDate() {
        val activityScenario = ActivityScenarioConfigurator.ForComposable()
            .setUiMode(UiMode.NIGHT)
            .launchConfiguredActivity()
            .onActivity {
                it.setContent {
                    composeTestRule.mainClock.autoAdvance = false
                    AppTheme {
                        Box(
                            modifier = Modifier.size(400.dp, height = 200.dp)
                                .background(MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            WeekCalendar(
                                startDate = testDate.minusDays(6),
                                selectedDate = testDate.minusDays(1),
                                onSelectedDateChange = {},
                                todayDate = testDate.minusDays(2)
                            )
                        }
                    }
                }
            }

        composeTestRule.mainClock.advanceTimeBy(400)
        activityScenario.waitForActivity()
        compareScreenshot(composeTestRule, "weekCalendar_dark_todayDateIsNotEqualToSelectedDate")

        activityScenario.close()
    }
}