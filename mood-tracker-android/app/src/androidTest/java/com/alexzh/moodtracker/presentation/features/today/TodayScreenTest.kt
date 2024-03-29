package com.alexzh.moodtracker.presentation.features.today

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.hasContentDescription
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.data.EmotionHistoryRepository
import com.alexzh.moodtracker.data.util.Result
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.di.runtimeDatabase
import com.alexzh.moodtracker.presentation.core.date.DateProvider
import com.alexzh.moodtracker.presentation.core.date.DateProviderImpl
import com.alexzh.moodtracker.presentation.feature.today.TodayScreen
import com.alexzh.moodtracker.presentation.feature.today.TodayViewModel
import com.alexzh.moodtracker.testData.EmotionHistoryTestData
import com.karumi.shot.ScreenshotTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.kotlin.any
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import java.time.LocalDate

@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
class TodayScreenTest : ScreenshotTest, KoinTest {
    private val testDate = LocalDate.of(2022, 3, 22)

    @get:Rule
    val composableTestRule = createComposeRule()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        stopKoin()
    }

    /**
     * Verify that [TodayScreen] displays a success state with a single emotion item.
     * - the "emotionHistoryRepo" provides a predefined set of fake data
     * - to verify emotion item we can use the [withEmotionStateAndNote] matcher
     */
    @Test
    fun displaySuccessWithSingleItem_whenDataIsAvailable() {
        val emotionHistoryRepo: EmotionHistoryRepository = mock {
            on { getEmotionsHistoryByDate(any(), any()) } doReturn
                flowOf(Result.Success(listOf(EmotionHistoryTestData.EMOTION_HISTORY_ITEM(testDate))))
            }

        startKoin {
            allowOverride(true)
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(
                listOf(
                    appModule,
                    dataModule,
                    module {
                        single { emotionHistoryRepo }
                        single<DateProvider> { DateProviderImpl(testDate) }
                    }
                )
            )
        }

        // ...
    }

    /**
     * Verify that [TodayScreen] displays a success state with multiple emotion items.
     * - the "emotionHistoryRepo" provides a predefined set of fake data
     * - to verify emotion item we can use the [withEmotionStateAndNote] matcher
     */
    @Test
    fun displaySuccessWithMultipleItems_whenDataIsAvailable() {

    }

    /**
     * Verify that [TodayScreen] displays a no data is available.
     * - the "emotionHistoryRepo" provides a predefined set of fake data
     * - to verify emotion item we can use the [withEmotionStateAndNote] matcher
     */
    @Test
    fun displayEmptyState_whenDataIsNotAvailable() {

    }

    private fun withEmotionStateAndNote(
        emotionState: String,
        note: String
    ): SemanticsMatcher {
        return hasText(note)
            .and(hasContentDescription(emotionState))
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}