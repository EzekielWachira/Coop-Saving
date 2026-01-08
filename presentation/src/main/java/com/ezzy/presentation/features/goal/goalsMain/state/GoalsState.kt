package com.ezzy.presentation.features.goal.goalsMain.state

import com.ezzy.domain.models.Contribution
import com.ezzy.domain.models.Goal
import com.ezzy.presentation.mviSetUp.MviState
import kotlin.collections.getOrNull

data class GoalsState(
    val goals: List<Goal> = emptyList(),
    val selectedGoalIndex: Int = 0,
    val contributions: List<Contribution> = emptyList(),
    val isLoading: Boolean = true
) : MviState {

    val selectedGoal: Goal?
        get() = goals.getOrNull(selectedGoalIndex)
}
