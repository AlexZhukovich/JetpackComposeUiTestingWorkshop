package com.alexzh.moodtracker.presentation.feature.home

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.alexzh.moodtracker.presentation.navigation.AppNavigation
import com.alexzh.moodtracker.presentation.navigation.Screens
import com.alexzh.moodtracker.presentation.theme.AppTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalComposeUiApi
@ExperimentalMaterial3Api
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            val items = listOf(
                Screens.TodayScreen.toNavItem(),
                Screens.StatisticsScreen.toNavItem(),
                Screens.SettingsScreen.toNavItem()
            )

            val isBottomNavBarDisplayed = rememberSaveable { mutableStateOf(true) }

            AppTheme {
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(
                            visible = isBottomNavBarDisplayed.value,
                            enter = slideInVertically(initialOffsetY = { it }),
                            exit = slideOutVertically(targetOffsetY = { it }),
                        ) {
                            NavigationBar(
                                containerColor = MaterialTheme.colorScheme.surface,
                            ) {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        selected = currentRoute == item.route,
                                        onClick = {
                                            navController.navigate(item.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                                  },
                                        icon = {
                                            Icon(
                                                painterResource(item.icon),
                                                contentDescription = stringResource(item.title)
                                            )
                                        },
                                        label = { Text(text = stringResource(item.title)) }
                                    )
                                }
                            }
                        }
                    }
                ) { internalPadding ->
                    Box(modifier = Modifier.padding(internalPadding)) {
                        AppNavigation(
                            navController,
                            isBottomNavBarDisplayed
                        )
                    }
                }
            }
        }
    }
}