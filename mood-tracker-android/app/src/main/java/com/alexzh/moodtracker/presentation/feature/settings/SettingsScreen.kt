package com.alexzh.moodtracker.presentation.feature.settings

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.alexzh.moodtracker.R

@ExperimentalMaterial3Api
@Composable
fun SettingsScreen(
    onProfile: () -> Unit,
    onDocs: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            SmallTopAppBar(
                modifier = Modifier.background(color = MaterialTheme.colorScheme.inversePrimary),
                title = { Text(stringResource(R.string.settingsScreen_title)) }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.fillMaxSize()
            .padding(
                start = 0.dp,
                top = paddingValues.calculateTopPadding(),
                end = 0.dp,
                bottom = paddingValues.calculateBottomPadding()
            )
        ) {
            Section(R.string.settingsScreen_accountSection_label) {
                SettingsItem(
                    title = R.string.settingsScreen_accountSection_profile_title,
                    subtitle = R.string.settingsScreen_accountSection_profile_subtitle,
                    icon = R.drawable.ic_settings_account,
                    onClick = { onProfile() })
            }
            Section(R.string.settingsScreen_faqSection_label) {
                SettingsItem(
                    title = R.string.settingsScreen_faqSection_configureAndroidProject_title,
                    subtitle = R.string.settingsScreen_faqSection_configureAndroidProject_subtitle,
                    icon = R.drawable.ic_android,
                    onClick = {
                        onDocs(
                            "https://github.com/AlexZhukovich/JetpackComposeUiTestingWorkshop/blob/main/mood-tracker-android/README.md#mood-tracker-android-app"
                        )
                    }
                )
                SettingsItem(
                    title = R.string.settingsScreen_faqSection_configureApi_title,
                    subtitle = R.string.settingsScreen_faqSection_configureApi_subtitle,
                    icon = R.drawable.ic_cloud_done,
                    onClick = {
                        onDocs(
                            "https://github.com/AlexZhukovich/JetpackComposeUiTestingWorkshop/blob/main/mood-tracker-api/README.md#mood-tracker-api"
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun SettingsItem(
    @StringRes title: Int,
    @StringRes subtitle: Int,
    @DrawableRes icon: Int,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
            .height(72.dp)
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Icon(
            modifier = Modifier.size(32.dp).align(Alignment.CenterVertically),
            painter = painterResource(icon),
            contentDescription = stringResource(R.string.settingsScreen_accountSection_profile_contentDescription),
            tint = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Column(
            modifier = Modifier.fillMaxWidth()
                .weight(1f)
                .padding(start = 8.dp, top = 4.dp, bottom = 4.dp)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(title)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(subtitle),
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
            )
        }
    }
}

@Composable
private fun Section(
    @StringRes title: Int,
    content: @Composable ColumnScope.() -> Unit
) {
    Column {
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp)
                .animateContentSize()
        ) {
            Text(
                modifier = Modifier.padding(start = 8.dp, bottom = 4.dp),
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(title)
            )
            content()
        }
    }
}