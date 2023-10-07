package com.alexzh.moodtracker.presentation.feature.stats

sealed class StatisticsEvent {
    data object LoadPreviousWeek : StatisticsEvent()
    data object LoadNextWeek: StatisticsEvent()
    data object LoadCurrentWeek : StatisticsEvent()
}
