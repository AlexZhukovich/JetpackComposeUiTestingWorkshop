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
import com.alexzh.moodtracker.presentation.feature.today.TodayScreen
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
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

    /**
     * Verify that [TodayScreen] displays emotion items after adding them on the [AddMoodScreen].
     * Instead of real environment, we will use the runtime database via [runtimeDatabase] koin module.
     * - to wait until data we will get data from database, we can use the "waitUntil" method
     * - to verify emotion item we can use the [withEmotionStateAndNote] matcher
     */
    @Test
    fun displayEmotion_WhenEmotionHistoryWasAddedViaAddMoodScreen() {

    }

    private fun withEmotionStateAndNote(
        emotionState: String,
        note: String
    ): SemanticsMatcher {
        return hasText(note)
            .and(hasContentDescription(emotionState))
    }
}