package com.alexzh.moodtracker.presentation.features.auth.login

import androidx.activity.ComponentActivity
import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.alexzh.moodtracker.presentation.navigation.Screens
import com.alexzh.moodtracker.presentation.theme.AppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
class LoginScreenTest : KoinTest {

    @get:Rule
    val composableTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(dataModule, appModule)
        }
    }

    /**
     * Verify that "The 'Email' should be at least four characters long" error message is displayed
     * when email is shorter than 4 symbols.
     */
    @Test
    fun displayEmailIsTooShortError_whenEnteredEmailIsShorterThanFourSymbols() {
        composableTestRule.apply {
            setContent {
                AppTheme {
                    AppNavigation(
                        navController = rememberNavController(),
                        isBottomBarDisplayed = remember { mutableStateOf(false) },
                        startDestination = Screens.LoginScreen
                    )
                }
            }

            onNodeWithText("Email")
                .performClick()
                .performTextInput("abc")

            onNodeWithText("LOGIN")
                .performClick()

            onNodeWithText("The 'Email' should be at least four characters long")
                .assertIsDisplayed()
        }
    }

    /**
     * Verify that "The 'Password' should be at least four characters long" error message is displayed
     * when password is shorter than 4 symbols.
     */
    @Test
    fun displayPasswordIsTooShortError_whenEnteredPasswordIsShorterThanFourSymbols() {
        composableTestRule.apply {
            setContent {
                AppTheme {
                    AppNavigation(
                        navController = rememberNavController(),
                        isBottomBarDisplayed = remember { mutableStateOf(false) },
                        startDestination = Screens.LoginScreen
                    )
                }
            }

            onNodeWithText("Email")
                .performClick()
                .performTextInput("test@test.test")

            onNodeWithText("Password")
                .performClick()
                .performTextInput("abc")

            onNodeWithText("LOGIN")
                .performClick()

            onNodeWithText("The 'Password' should be at least four characters long")
                .assertIsDisplayed()
        }
    }
}