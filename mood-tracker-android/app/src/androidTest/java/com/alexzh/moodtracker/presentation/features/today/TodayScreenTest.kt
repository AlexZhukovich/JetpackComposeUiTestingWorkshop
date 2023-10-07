package com.alexzh.moodtracker.presentation.features.today

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.data.EmotionHistoryRepository
import com.alexzh.moodtracker.data.util.Result
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.presentation.core.date.DateProvider
import com.alexzh.moodtracker.presentation.core.date.DateProviderImpl
import com.alexzh.moodtracker.presentation.feature.today.TodayScreen
import com.alexzh.moodtracker.testData.EmotionHistoryTestData
import com.karumi.shot.ScreenshotTest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
    private val testDispatcher: CoroutineDispatcher = Dispatchers.Unconfined

    @get:Rule
    val composableTestRule = createComposeRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        stopKoin()
    }

    @Test
    fun displaySuccessWithSimpleItem_whenDataIsAvailable() {
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

        composableTestRule.apply {
            setContent {
                TodayScreen(
                    viewModel = get(),
                    onAdd = {},
                    onEdit = {}
                )
            }

            onNodeWithContentDescription("Excited")
                .assertIsDisplayed()
        }
    }

    @Test
    fun displaySuccessWithMultipleItems_whenDataIsAvailable() {
        val emotionHistoryRepo: EmotionHistoryRepository = mock {
            on { getEmotionsHistoryByDate(any(), any()) } doReturn
                    flowOf(Result.Success(EmotionHistoryTestData.EMOTION_HISTORY_ITEMS(testDate)))
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

        composableTestRule.apply {
            setContent {
                TodayScreen(
                    viewModel = get(),
                    onAdd = {},
                    onEdit = {}
                )
            }

            onAllNodesWithContentDescription("Excited")
                .assertCountEquals(2)
        }
    }

    @Test
    fun displayEmptyState_whenDataIsNotAvailable() {
        val emotionHistoryRepo: EmotionHistoryRepository = mock {
            on { getEmotionsHistoryByDate(any(), any()) } doReturn
                    flowOf(Result.Success(emptyList()))
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

        composableTestRule.apply {
            setContent {
                TodayScreen(
                    viewModel = get(),
                    onAdd = {},
                    onEdit = {}
                )
            }

            onNodeWithText("No data")
                .assertIsDisplayed()
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}