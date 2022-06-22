package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.alexzh.moodtracker.presentation.navigation.Screens
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
open class BaseOperation(
    private val composeTestRule: ComposeContentTestRule,
) {

    fun launchScreen(screen: Screens) {
        composeTestRule.setContent {
            AppNavigation(
                navController = rememberAnimatedNavController(),
                isBottomBarDisplayed = remember { mutableStateOf(false) },
                startDestination = screen
            )
        }
    }

    fun clickOnText(text: String) {
        composeTestRule.onNodeWithText(text)
            .performClick()
    }

    fun hasText(text: String) {
        composeTestRule.onNodeWithText(text)
            .assertIsDisplayed()
    }

    fun enterTextByLabel(label: String, text: String) {
        composeTestRule.onNodeWithText(label)
            .performTextInput(text)
    }
}