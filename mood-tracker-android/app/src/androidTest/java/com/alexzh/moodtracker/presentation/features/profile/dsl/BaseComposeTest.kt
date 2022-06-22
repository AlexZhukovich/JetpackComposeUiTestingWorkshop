package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import org.junit.Rule


open class BaseComposeTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
}