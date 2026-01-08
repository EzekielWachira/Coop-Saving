package com.ezzy.presentation.features.goal.withdraw.events

import com.ezzy.presentation.mviSetUp.MviEvent

sealed interface WithdrawEvent : MviEvent {

    data object NavigateBack : WithdrawEvent

    data object NavigateToGoals : WithdrawEvent

    data class ShowError(val message: String) : WithdrawEvent
}
