package com.alexzh.moodtracker.presentation.feature.profile

sealed class ProfileEvent {
    data object GetUserInfo: ProfileEvent()
    data object LogOut: ProfileEvent()
}
