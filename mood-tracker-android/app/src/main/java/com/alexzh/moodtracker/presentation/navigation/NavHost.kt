package com.alexzh.moodtracker.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.alexzh.moodtracker.presentation.feature.addmood.AddMoodScreen
import com.alexzh.moodtracker.presentation.feature.auth.createaccount.CreateAccountScreen
import com.alexzh.moodtracker.presentation.feature.auth.login.LoginScreen
import com.alexzh.moodtracker.presentation.feature.docs.DocumentationScreen
import com.alexzh.moodtracker.presentation.feature.profile.ProfileScreen
import com.alexzh.moodtracker.presentation.feature.settings.SettingsScreen
import com.alexzh.moodtracker.presentation.feature.stats.StatisticsScreen
import com.alexzh.moodtracker.presentation.feature.today.TodayScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.jetbrains.annotations.TestOnly
import org.koin.androidx.compose.get

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
@Composable
fun AppNavigation(
    navController: NavHostController,
    isBottomBarDisplayed: MutableState<Boolean>,
    startDestination: Screens = Screens.TodayScreen
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination.route,
        enterTransition = { fadeIn(animationSpec = tween(400)) },
        exitTransition = { fadeOut(animationSpec = tween(400)) }
    ) {
        composable(
            route = Screens.TodayScreen.route,
        ) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = true
            }
            TodayScreen(
                viewModel = get(),
                onAdd = { navController.navigate(Screens.AddMoodScreen.createRoute(-1L)) },
                onEdit = { navController.navigate(Screens.AddMoodScreen.createRoute(it)) }
            )
        }

        composable(
            route = Screens.AddMoodScreen.route,
            arguments = listOf(
                navArgument(Screens.AddMoodScreen.paramName) {
                    type = NavType.LongType
                }
            )
        ) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = false
            }
            AddMoodScreen(
                emotionHistoryId = it.arguments?.getLong(Screens.AddMoodScreen.paramName) ?: -1,
                viewModel = get(),
                onBack = { navController.navigateUp() }
            )
        }

        composable(route = Screens.StatisticsScreen.route) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = true
            }
            StatisticsScreen(
                viewModel = get()
            )
        }

        composable(route = Screens.SettingsScreen.route) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = true
            }
            SettingsScreen(
                onProfile = { navController.navigate(Screens.ProfileScreen.route) },
                onDocs = { url ->
                    navController.navigate(Screens.DocumentationScreen.createRoute(url))
                }
            )
        }

        composable(route = Screens.ProfileScreen.route) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = false
            }
            ProfileScreen(
                viewModel = get(),
                onCreateAccount = { navController.navigate(Screens.CreateAccountScreen.route) },
                onLogin = { navController.navigate(Screens.LoginScreen.createRoute(Screens.ProfileScreen.route)) },
                onBack = { navController.navigateUp() }
            )
        }

        composable(route = Screens.CreateAccountScreen.route) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = false
            }
            CreateAccountScreen(
                viewModel = get(),
                onLogin = { navController.navigate(Screens.LoginScreen.route) },
                onCreateAccountSuccess = { navController.navigate(Screens.TodayScreen.route) },
                onContinueWithoutAccount = { navController.navigate(Screens.TodayScreen.route) }
            )
        }

        composable(
            route = Screens.LoginScreen.route,
            arguments = listOf(
                navArgument(Screens.LoginScreen.paramName) {
                    type = NavType.StringType
                }
            )
        ) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = false
            }
            LoginScreen(
                viewModel = get(),
                redirectTo = it.arguments?.getString(Screens.LoginScreen.paramName) ?: Screens.LoginScreen.NO_REDIRECT,
                onLoginSuccess = { redirectTo ->
                    if (redirectTo != Screens.LoginScreen.NO_REDIRECT) {
                        navController.navigate(redirectTo) {
                            popUpTo(redirectTo) { inclusive = true }
                        }
                    } else navController.navigate(Screens.TodayScreen.route)
                },
                onCreateAccount = { navController.navigate(Screens.CreateAccountScreen.route) },
                onContinueWithoutAccount = { navController.navigate(Screens.TodayScreen.route) }
            )
        }

        composable(
            route = Screens.DocumentationScreen.route,
            arguments = listOf(
                navArgument(Screens.DocumentationScreen.paramUrl) { type = NavType.StringType},
            )
        ) {
            LaunchedEffect(Unit) {
                isBottomBarDisplayed.value = false
            }
            DocumentationScreen(
                url = it.arguments?.getString(Screens.DocumentationScreen.paramUrl) ?: "",
                onBack = { navController.navigateUp() }
            )
        }
    }
}