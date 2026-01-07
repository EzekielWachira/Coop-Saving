package com.ezzy.presentation.features.goal.createGoal.events

import com.ezzy.presentation.mviSetUp.MviEvent

sealed interface CreateGoalEvent : MviEvent {
    data object NavigateToMyGoals : CreateGoalEvent
    data object NavigateBack : CreateGoalEvent
    data class ShowError(val message: String) : CreateGoalEvent
}