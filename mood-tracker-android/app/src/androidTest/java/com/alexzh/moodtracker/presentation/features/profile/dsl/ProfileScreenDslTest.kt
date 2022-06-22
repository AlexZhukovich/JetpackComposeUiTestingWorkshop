package com.alexzh.moodtracker.presentation.features.profile.dsl

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.ExperimentalComposeUiApi
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
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.util.*

@ExperimentalMaterial3Api
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@RunWith(AndroidJUnit4::class)
class ProfileScreenDslTest: BaseComposeTest() {

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

    @Test
    fun shouldBeDisplayedUsedInformation_WhenUserIsLoggedIn() {
        val email = "test@test.com"
        val password = "test"
        val username = "Test"

        whenever(userRepository.getUserInfo())
            .thenReturn(flowOf(Result.Error(Unauthorized())))
            .thenReturn(flowOf(Result.Success(UserInfoModel(email, username))))
        whenever(authRepository.logIn(email, password))
            .thenReturn(flowOf(Result.Success(JwtToken(UUID.randomUUID().toString()))))

        profileScreen {
            launch()
            hasTitle()
            tapOnLogin()
        }
        loginScreen {
            login(email, password)
        }
        profileScreen {
            hasUserInfo(email, username)
        }
    }
}