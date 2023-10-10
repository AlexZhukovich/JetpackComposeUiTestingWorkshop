package com.alexzh.moodtracker.presentation.features.profile

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
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
class ProfileScreenTest: KoinTest {

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
     * Verify that [ProfileScreen] display a user information
     * when user is logged in.
     */
    @Test
    fun displayUserInfo_WhenUserIsLoggedIn() {

    }
}