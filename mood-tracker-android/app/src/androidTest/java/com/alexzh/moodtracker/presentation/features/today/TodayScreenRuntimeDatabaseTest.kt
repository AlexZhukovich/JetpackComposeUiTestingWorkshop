package com.alexzh.moodtracker.presentation.features.today

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.MoodTrackerDatabase
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.di.runtimeDatabase
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class TodayScreenRuntimeDatabaseTest: KoinTest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @Test
    fun displayEmotion_WhenEmotionHistoryWasAddedViaAddMoodScreen() {
        stopKoin()
        startKoin {
            allowOverride(true)
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(
                dataModule, appModule, runtimeDatabase
            )
        }

        composableTestRule.apply {
            setContent {
                AppNavigation(
                    navController = rememberAnimatedNavController(),
                    isBottomBarDisplayed = remember { mutableStateOf(false) }
                )
            }

            waitUntil {
                onAllNodesWithText("Emotions")
                    .fetchSemanticsNodes().size == 1
            }

            onNode(hasText("Add"))
                .performClick()

            waitUntil {
                onAllNodesWithContentDescription("Happy")
                    .fetchSemanticsNodes().size == 1
            }

            onNodeWithContentDescription("Happy")
                .performClick()

            onNodeWithText("Reading")
                .performClick()

            onNodeWithText("Gaming")
                .performClick()

            onNodeWithText("Note")
                .performTextInput("Test note")

            onNode(hasText("Save"))
                .performScrollTo()
                .performClick()

            onRoot().printToLog("MERGED")

            waitUntil {
                onAllNodesWithText("Emotions")
                    .fetchSemanticsNodes().size == 1
            }

            onNode(withEmotionStateAndNote("Happy", "Test note"))
                .assert(hasAnyChild(hasText("Reading")))
                .assert(hasAnyChild(hasText("Gaming")))
        }
    }

    fun withEmotionStateAndNote(
        emotionState: String,
        note: String
    ): SemanticsMatcher {
        return hasText(note)
            .and(hasContentDescription(emotionState))
    }
}