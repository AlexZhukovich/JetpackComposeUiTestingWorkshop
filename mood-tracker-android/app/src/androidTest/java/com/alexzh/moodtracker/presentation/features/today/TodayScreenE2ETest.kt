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
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.junit.Rule
import org.junit.Test

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
class TodayScreenE2ETest {

    @get:Rule
    val composableTestRule = createComposeRule()

    @Test
    fun displayEmotion_WhenEmotionHistoryWasAddedViaAddMoodScreen() {

    }

    fun withEmotionStateAndNote(
        emotionState: String,
        note: String
    ): SemanticsMatcher {
        return hasText(note)
            .and(hasContentDescription(emotionState))
    }
}