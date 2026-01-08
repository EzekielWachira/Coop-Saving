package com.ezzy.presentation.features.goal.goalsMain.viewModel

import androidx.lifecycle.viewModelScope
import com.ezzy.domain.enums.GoalStatus
import com.ezzy.domain.repository.ContributionRepository
import com.ezzy.domain.repository.GoalRepository
import com.ezzy.presentation.features.goal.goalsMain.action.GoalsAction
import com.ezzy.presentation.features.goal.goalsMain.events.GoalsEvent
import com.ezzy.presentation.features.goal.goalsMain.state.GoalsState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoalsViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val contributionRepository: ContributionRepository
) : BaseMviViewModel<
        GoalsState,
        GoalsAction,
        GoalsEvent
        >(GoalsState()) {


    init {
        dispatch(GoalsAction.LoadGoals)
    }

    override fun onUnhandledAction(action: GoalsAction) {
        when (action) {
            GoalsAction.LoadGoals -> loadGoals()

            GoalsAction.OnAddGoalClicked ->
                sendEvent(GoalsEvent.NavigateToCreateGoal)

            is GoalsAction.OnDepositClicked ->
                sendEvent(
                    GoalsEvent.NavigateToDeposit(action.goalId)
                )

            is GoalsAction.OnWithdrawClicked ->
                sendEvent(
                    GoalsEvent.NavigateToWithdraw(action.goalId)
                )

            else -> Unit
        }
    }


    private fun loadGoals() {
        viewModelScope.launch {
            goalRepository
                .getGoals(GoalStatus.ACTIVE)
                .collect { goals ->

                    reduce {
                        copy(
                            goals = goals,
                            isLoading = false,
                            selectedGoalIndex =
                                selectedGoalIndex
                                    .coerceAtMost(goals.lastIndex)
                                    .coerceAtLeast(0)
                        )
                    }

                    // Load contributions for selected goal
                    loadContributionsForSelectedGoal()
                }
        }
    }


    private fun loadContributionsForSelectedGoal() {
        val goalId = currentState.selectedGoal?.id ?: return

        viewModelScope.launch {
            contributionRepository
                .getContributionsForGoal(goalId)
                .collect { contributions ->
                    reduce {
                        copy(contributions = contributions)
                    }
                }
        }
    }


    init {
        registerReducer(GoalsAction.OnGoalSelected::class) { action ->
            copy(selectedGoalIndex = action.index)
        }
    }

}