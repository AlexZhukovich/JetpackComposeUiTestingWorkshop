package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.data.AuthRepository
import com.alexzh.moodtracker.data.UserRepository
import com.alexzh.moodtracker.data.exception.Unauthorized
import com.alexzh.moodtracker.data.model.JwtToken
import com.alexzh.moodtracker.data.remote.model.UserInfoModel
import com.alexzh.moodtracker.data.util.Result
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.presentation.feature.auth.login.LoginScreen
import com.alexzh.moodtracker.presentation.feature.profile.ProfileScreen
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.alexzh.moodtracker.presentation.navigation.Screens
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
class ProfileScreenDslTest: KoinTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val authRepository: AuthRepository = mock()
    private val userRepository: UserRepository = mock()

    @Before
    fun setup() {
        stopKoin()
        startKoin {
            allowOverride(true)
            androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
            modules(
                dataModule,
                appModule,
                module {
                    single { authRepository }
                    single { userRepository }
                }
            )
        }
    }

    /**
     * Verify that [ProfileScreen] allows to login into the application when user is not logged in.
     * When the user is logged in the personal information is displayed.
     *
     * The DSL should be created with the "Robot" pattern.
     *
     * Flow for this test case:
     * - [ProfileScreen]: user is not logged in (default state)
     * - [LoginScreen]: enter credentials and login to the app
     * - [ProfileScreen]: user and logged in and user information is displayed
     */
    @Test
    fun shouldBeDisplayedUsedInformation_WhenUserIsLoggedIn() {
        whenever(userRepository.getUserInfo())
            .thenReturn(flowOf(Result.Error(Unauthorized())))
            .thenReturn(flowOf(Result.Success(UserInfoModel("test-account@alexzh.com", "Test User"))))
        whenever(authRepository.logIn("test-account@alexzh.com", "test-password"))
            .thenReturn(flowOf(Result.Success(JwtToken(UUID.randomUUID().toString()))))


        composeTestRule.apply {
            setContent {
                AppNavigation(
                    navController = rememberNavController(),
                    isBottomBarDisplayed = remember { mutableStateOf(false) },
                    startDestination = Screens.ProfileScreen
                )
            }

            profileScreen(composeTestRule) {
                tapOnLogin()
            }
            loginScreen(composeTestRule) {
                login(
                    email = "test-account@alexzh.com",
                    password = "test-password"
                )
            }
            profileScreen(composeTestRule) {
                hasUserInfo(
                    email = "test-account@alexzh.com",
                    name = "Test User"
                )
            }
        }
    }

    open class BaseOperations(private val composableRule: ComposeContentTestRule) {

        fun enterText(inputFieldLabel: String, text: String) {
            composableRule.onNodeWithText(inputFieldLabel)
                .performTextInput(text)
        }

        fun hasText(text: String) {
            composableRule.onNodeWithText(text)
                .assertIsDisplayed()
        }

        fun clickOnText(text: String) {
            composableRule.onNodeWithText(text)
                .performClick()
        }
    }

    fun profileScreen(
        composableRule: ComposeContentTestRule,
        func: ProfileScreenRobot.() -> Unit
    ) = ProfileScreenRobot(composableRule).apply(func)

    class ProfileScreenRobot(
        composableRule: ComposeContentTestRule
    ) : BaseOperations(composableRule) {

        fun tapOnLogin() {
            clickOnText("Login")
        }

        fun hasUserInfo(
            email: String,
            name: String
        ) {
            hasText(email)
            hasText(name)
        }
    }

    fun loginScreen(
        composableRule: ComposeContentTestRule,
        func: LoginScreenRobot.() -> Unit
    ) = LoginScreenRobot(composableRule).apply(func)

    class LoginScreenRobot(
        composableRule: ComposeContentTestRule
    ) : BaseOperations(composableRule) {

        fun login(
            email: String,
            password: String
        ) {
            enterText("Email", email)
            enterText("Password", password)
            clickOnText("LOGIN")
        }
    }
}