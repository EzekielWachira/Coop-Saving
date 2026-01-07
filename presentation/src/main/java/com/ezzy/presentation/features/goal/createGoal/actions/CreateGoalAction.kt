package com.ezzy.presentation.features.goal.createGoal.actions

import com.ezzy.domain.enums.GoalCategory
import com.ezzy.presentation.mviSetUp.MviAction
import java.time.LocalDate

sealed interface CreateGoalAction : MviAction {



    data class OnNameChange(val value: String) : CreateGoalAction
    data class OnCategorySelected(val category: GoalCategory) : CreateGoalAction
    data class OnTargetAmountChange(val value: String) : CreateGoalAction

    data object OnDateClicked : CreateGoalAction
    data class OnDateSelected(val date: LocalDate) : CreateGoalAction
    data object DismissDatePicker : CreateGoalAction

    data object Submit : CreateGoalAction
    data object DismissSuccessDialog : CreateGoalAction
    data object GoToMyGoals : CreateGoalAction
    data object NavigateBack : CreateGoalAction
}