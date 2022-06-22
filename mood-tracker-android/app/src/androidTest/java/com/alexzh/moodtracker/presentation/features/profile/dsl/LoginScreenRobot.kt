package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.junit4.ComposeContentTestRule

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
class LoginScreenRobot(
    composeContentTestRule: ComposeContentTestRule
) : BaseOperation(composeContentTestRule) {

    fun login(email: String, password: String) {
        enterTextByLabel("Email", email)
        enterTextByLabel("Password", password)
        clickOnText("LOGIN")
    }
}

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
internal fun BaseComposeTest.loginScreen(
    func: LoginScreenRobot.() -> Unit
) = LoginScreenRobot(composeTestRule).apply(func)