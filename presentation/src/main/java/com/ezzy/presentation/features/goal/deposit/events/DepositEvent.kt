package com.ezzy.presentation.features.goal.deposit.events

import com.ezzy.presentation.mviSetUp.MviEvent

sealed interface DepositEvent : MviEvent {
    data object NavigateBack : DepositEvent
    data object NavigateToGoals : DepositEvent
    data class ShowError(val message: String) : DepositEvent
}
