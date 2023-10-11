package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.activity.ComponentActivity
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.alexzh.moodtracker.presentation.navigation.Screens
import org.junit.Rule

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class ProfileScreenRobot(
    composeContentTestRule: ComposeContentTestRule
) : BaseOperation(composeContentTestRule) {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    fun launch() {
        launchScreen(Screens.ProfileScreen)
    }

    fun tapOnLogin() {
        clickOnText("Login")
    }

    fun hasTitle() {
        hasText("Profile")
    }

    fun hasUserInfo(email: String, name: String) {
        hasText(email)
        hasText(name)
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
internal fun BaseComposeTest.profileScreen(
    func: ProfileScreenRobot.() -> Unit
) = ProfileScreenRobot(composeTestRule).apply(func)