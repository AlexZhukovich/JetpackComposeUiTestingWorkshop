package com.alexzh.moodtracker.presentation.features.auth.login

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.platform.app.InstrumentationRegistry
import com.alexzh.moodtracker.R
import com.alexzh.moodtracker.di.appModule
import com.alexzh.moodtracker.di.dataModule
import com.alexzh.moodtracker.presentation.feature.auth.login.LoginScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get

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

    @Test
    fun displayEmailIsTooShortError_whenEnteredEmailIsShorterThanFourSymbols() {

    }

    @Test
    fun displayPasswordIsTooShortError_whenEnteredPasswordIsShorterThanFourSymbols() {

    }
}