package com.alexzh.moodtracker.presentation.feature.today

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzh.moodtracker.R
import com.alexzh.moodtracker.data.EmotionHistoryRepository
import com.alexzh.moodtracker.data.local.adapter.DATE_TIME_ZONE_UTC
import com.alexzh.moodtracker.data.model.EmotionHistory
import com.alexzh.moodtracker.data.util.Result
import com.alexzh.moodtracker.presentation.core.date.DateProvider
import com.alexzh.moodtracker.presentation.core.icon.ActivityIconMapper
import com.alexzh.moodtracker.presentation.core.icon.EmotionContentDescriptionMapper
import com.alexzh.moodtracker.presentation.core.icon.EmotionIconMapper
import com.alexzh.moodtracker.presentation.feature.today.model.EmotionHistoryItem
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class TodayViewModel(
    private val emotionHistoryRepository: EmotionHistoryRepository,
    private val emotionIconMapper: EmotionIconMapper,
    private val activityIconMapper: ActivityIconMapper,
    private val emotionContentDescriptionMapper: EmotionContentDescriptionMapper,
    dateProvider: DateProvider
) : ViewModel() {
    private val _uiState = mutableStateOf(TodayScreenState(date = dateProvider.getCurrentDate()))
    val uiState: State<TodayScreenState> = _uiState

    init {
        fetchMoodHistory(_uiState.value.date)
    }

    fun onEvent(event: TodayEvent) {
        when (event) {
            is TodayEvent.OnDateChange -> fetchMoodHistory(event.date)
        }
    }

    // TODO: ADD SUPPORT FOR 12 AND 24 HOURS FORMATS
    private fun fetchMoodHistory(date: LocalDate) {
        _uiState.value = _uiState.value.copy(isLoading = true, date = date, items = emptyList())
        viewModelScope.launch {
            val startDate = ZonedDateTime.of(date, LocalTime.of(0, 0, 0), DATE_TIME_ZONE_UTC)
            val endDate = ZonedDateTime.of(date, LocalTime.of(23, 59, 59), DATE_TIME_ZONE_UTC)
            emotionHistoryRepository.getEmotionsHistoryByDate(startDate, endDate).collect { result ->
                when (result) {
                    is Result.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            items = mapToEmotionHistoryItem(result.data)
                        )
                    }
                    is Result.Error -> {
                        // TODO: IMPLEMENT IT
                    }
                }
            }
        }
    }

    private fun mapToEmotionHistoryItem(
        data: List<EmotionHistory>
    ): List<EmotionHistoryItem> {
        return data.map { item ->
            EmotionHistoryItem(
                id = item.id,
                note = item.note,
                iconId = emotionIconMapper.mapToEmotionItem(
                    item.emotion,
                    emotionContentDescriptionMapper.mapHappinessToContentDescription(
                        item.emotion.happinessLevel,
                        R.string.emotions_unknown_contentDescription
                    ),
                    R.drawable.ic_question_mark
                ).iconRes,
                iconContentDescription = emotionContentDescriptionMapper.mapHappinessToContentDescription(item.emotion.happinessLevel, R.string.emotions_unknown_contentDescription),
                formattedDate = item.date.format(DateTimeFormatter.ofPattern("HH:mm")),
                activities = item.activities.map {
                    activityIconMapper.mapToActivityItem(it, R.drawable.ic_question_mark)
                }
            )
        }
    }
}