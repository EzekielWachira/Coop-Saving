package com.ezzy.presentation.features.goal.deposit.actions

import com.ezzy.presentation.features.goal.deposit.enums.FundSource
import com.ezzy.presentation.mviSetUp.MviAction

sealed interface DepositAction : MviAction {

    data object Load : DepositAction

    data class GoalSelected(val goalId: Long) : DepositAction

    data class FundSourceSelected(val source: FundSource) : DepositAction

    data class AmountChanged(val value: String) : DepositAction

    data object Submit : DepositAction

    data object DismissSuccess : DepositAction

    data object GoToGoals : DepositAction

    data object NavigateBack : DepositAction
}
