package com.alexzh.moodtracker.presentation.component.calendar

import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createEmptyComposeRule
import androidx.compose.ui.unit.dp
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

    @Test
    fun weekCalendar_light_todayInSelectedDate() {

    }

    @Test
    fun weekCalendar_dark_todayInSelectedDate() {

    }

    @Test
    fun weekCalendar_light_todayInNotSelectedDate() {

    }

    @Test
    fun weekCalendar_dark_todayInNotSelectedDate() {

    }
}