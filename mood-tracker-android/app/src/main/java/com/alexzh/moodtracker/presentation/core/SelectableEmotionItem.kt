package com.alexzh.moodtracker.presentation.core

import androidx.annotation.DrawableRes

data class SelectableEmotionItem(
    val emotionId: Long,
    @DrawableRes val iconRes: Int,
    val isSelected: Boolean
)
