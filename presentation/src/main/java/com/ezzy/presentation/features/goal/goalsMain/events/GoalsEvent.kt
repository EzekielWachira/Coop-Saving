package com.ezzy.presentation.features.goal.goalsMain.events

import com.ezzy.presentation.mviSetUp.MviEvent

sealed interface GoalsEvent : MviEvent {

    data object NavigateToCreateGoal : GoalsEvent

    data class NavigateToDeposit(val goalId: Long) : GoalsEvent

    data class NavigateToWithdraw(val goalId: Long) : GoalsEvent
}
