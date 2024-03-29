package com.alexzh.moodtracker.presentation.feature.auth.login

sealed class LoginEvent {
    data object Login: LoginEvent()

    data class EmailChange(
        val email: String
    ): LoginEvent()

    data class PasswordChange(
        val password: String
    ): LoginEvent()
}