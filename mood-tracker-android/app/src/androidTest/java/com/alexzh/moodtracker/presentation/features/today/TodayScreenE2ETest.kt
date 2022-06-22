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

            waitUntil {
                onAllNodesWithText("Emotions")
                    .fetchSemanticsNodes().size == 1
            }

            onNode(withEmotionStateAndNote("Happy", "Test note"))
                .assert(hasAnyChild(hasText("Reading")))
                .assert(hasAnyChild(hasText("Gaming")))

            // end of test case
            // clean up
            onRoot().printToLog("MERGED")

            onNode(hasContentDescription("Happy"))
                .performSemanticsAction(SemanticsActions.OnClick)

            waitUntil {
                onAllNodesWithContentDescription("Happy")
                    .fetchSemanticsNodes().size == 1
            }

            onNodeWithContentDescription("Delete")
                .performClick()
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