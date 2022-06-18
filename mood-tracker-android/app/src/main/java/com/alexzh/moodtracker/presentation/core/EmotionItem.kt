package com.alexzh.moodtracker.presentation.core

import androidx.annotation.DrawableRes

data class EmotionItem(
    val emotionId: Long,
    @DrawableRes val iconRes: Int,
)
