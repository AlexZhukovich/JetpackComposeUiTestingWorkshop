package com.alexzh.moodtracker.presentation.feature.profile

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexzh.moodtracker.R
import com.alexzh.moodtracker.data.remote.model.UserInfoModel
import kotlinx.coroutines.delay

@ExperimentalMaterial3Api
@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel,
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit,
    onBack: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.state

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.inversePrimary),
                title = { Text(stringResource(R.string.profileScreen_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.navigation_back_contentDescription)
                        )
                    }
                },
                actions = {
                    if (uiState.userInfoModel != null) {
                        IconButton(onClick = { viewModel.onEvent(ProfileEvent.LogOut) }) {
                            Icon(
                                painter = painterResource(R.drawable.ic_logout),
                                contentDescription = stringResource(R.string.profileScreen_logout_contentDescription)
                            )
                        }
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                snackbar = { Snackbar(it) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.fillMaxSize()
                .padding(
                    start = 8.dp,
                    top = paddingValues.calculateTopPadding(),
                    end = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when {
                uiState.loading -> LoadingScreen()
                uiState.userInfoModel != null -> {
                    uiState.userInfoModel?.let {
                        SuccessScreen(it)
                    }
                }
                else -> {
                    ErrorScreen(
                        errorMessage = uiState.errorMessage,
                        snackbarHostState = snackbarHostState,
                        onCreateAccount = onCreateAccount,
                        onLogin = onLogin
                    )
                }
            }
        }
    }
    LaunchedEffect(Unit) {
        viewModel.onEvent(ProfileEvent.GetUserInfo)
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorScreen(
    errorMessage: Int?,
    snackbarHostState: SnackbarHostState,
    onCreateAccount: () -> Unit,
    onLogin: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onCreateAccount() }
        ) {
            Text("CREATE ACCOUNT SCREEN")
        }

        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { onLogin() }
        ) {
            Text("LOGIN SCREEN")
        }

        errorMessage?.let {
            val text = stringResource(it)
            LaunchedEffect(Unit) {
                delay(400)
                snackbarHostState.showSnackbar(text)
            }
        }
    }
}

@Composable
private fun SuccessScreen(
    userInfoModel: UserInfoModel,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        UserInfoContainer(userInfoModel)
    }
}

@Composable
private fun UserInfoContainer(
    userInfoModel: UserInfoModel
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        UserInfoRow(
            icon = R.drawable.ic_person,
            vale = userInfoModel.username,
            contentDescription = stringResource(R.string.genericTextField_name_contentDescription)
        )
        UserInfoRow(
            icon = R.drawable.ic_email,
            vale = userInfoModel.email,
            contentDescription = stringResource(R.string.genericTextField_email_contentDescription)
        )
    }
}

@Composable
private fun UserInfoRow(
    @DrawableRes icon: Int,
    vale: String,
    contentDescription: String
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(56.dp)
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(48.dp),
            painter = painterResource(icon),
            contentDescription = contentDescription
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(vale)
    }
}