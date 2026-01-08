package com.ezzy.presentation.features.goal.goalsMain.action

import com.ezzy.presentation.mviSetUp.MviAction

sealed interface GoalsAction : MviAction {

    data object LoadGoals : GoalsAction

    data class OnGoalSelected(val index: Int) : GoalsAction

    data object OnAddGoalClicked : GoalsAction

    data class OnDepositClicked(val goalId: Long) : GoalsAction

    data class OnWithdrawClicked(val goalId: Long) : GoalsAction
}
