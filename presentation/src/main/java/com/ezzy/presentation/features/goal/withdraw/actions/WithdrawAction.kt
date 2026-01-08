package com.ezzy.presentation.features.goal.withdraw.actions

import com.ezzy.presentation.features.goal.withdraw.enums.WithdrawDestination
import com.ezzy.presentation.mviSetUp.MviAction

sealed interface WithdrawAction : MviAction {

    data object LoadGoals : WithdrawAction

    data class OnGoalSelected(val goalId: Long) : WithdrawAction

    data class OnWithdrawDestinationSelected(
        val destination: WithdrawDestination
    ) : WithdrawAction

    data class OnPhoneNumberChange(val value: String) : WithdrawAction

    data class OnAmountChange(val value: String) : WithdrawAction

    data object SubmitWithdraw : WithdrawAction

    data object DismissSuccessDialog : WithdrawAction

    data object GoToMyGoals : WithdrawAction

    data object NavigateBack : WithdrawAction
}
