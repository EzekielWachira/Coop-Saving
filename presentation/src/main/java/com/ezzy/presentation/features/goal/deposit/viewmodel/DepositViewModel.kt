package com.ezzy.presentation.features.goal.deposit.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ezzy.domain.repository.ContributionRepository
import com.ezzy.domain.repository.GoalRepository
import com.ezzy.presentation.features.goal.deposit.actions.DepositAction
import com.ezzy.presentation.features.goal.deposit.enums.FundSource
import com.ezzy.presentation.features.goal.deposit.events.DepositEvent
import com.ezzy.presentation.features.goal.deposit.state.DepositState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepositViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val contributionRepository: ContributionRepository,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<
        DepositState,
        DepositAction,
        DepositEvent
        >(DepositState()) {

    private val initialGoalId: Long =
        savedStateHandle["goalId"]
            ?: error("goalId is required")

    init {
        registerReducers()
        dispatch(DepositAction.Load)
        dispatch(DepositAction.GoalSelected(initialGoalId))
    }

    private fun registerReducers() {
        registerReducer(DepositAction.GoalSelected::class) {
            copy(selectedGoalId = it.goalId)
        }

        registerReducer(DepositAction.FundSourceSelected::class) {
            copy(fundFrom = it.source)
        }

        registerReducer(DepositAction.AmountChanged::class) {
            copy(amount = it.value)
        }

        registerReducer(DepositAction.Submit::class) {
            if (!isValid()) {
                sendEvent(
                    DepositEvent.ShowError("Invalid deposit amount")
                )
                this
            } else {
                submitDeposit()
            }
        }

        registerReducer(DepositAction.DismissSuccess::class) {
            copy(showSuccessDialog = false)
        }

        registerReducer(DepositAction.GoToGoals::class) {
            sendEvent(DepositEvent.NavigateToGoals)
            copy(showSuccessDialog = false)
        }
    }

    override fun onUnhandledAction(action: DepositAction) {
        when (action) {
            DepositAction.Load -> loadGoals()
            DepositAction.NavigateBack ->
                sendEvent(DepositEvent.NavigateBack)

            else -> Unit
        }
    }

    private fun loadGoals() {
        viewModelScope.launch {
            goalRepository.getGoals().collect { goals ->
                reduce {
                    copy(
                        goals = goals,
                        selectedGoalId =
                            selectedGoalId ?: initialGoalId
                    )
                }
            }
        }
    }

    private fun DepositState.isValid(): Boolean =
        amount.toDoubleOrNull()?.let { it > 0 } == true &&
                (fundFrom != FundSource.COOP_ACCOUNT ||
                        amount.toDouble() <= (creditAccount?.balance ?: 0.0))

    private fun DepositState.submitDeposit(): DepositState {

        viewModelScope.launch {
            try {
                contributionRepository.addContribution(
                    goalId = selectedGoalId!!,
                    amount = amount.toDouble()
                )

                reduce {
                    copy(
                        isSubmitting = false,
                        showSuccessDialog = true,
                        depositedAmount = amount.toDouble()
                    )
                }

            } catch (t: Throwable) {
                reduce { copy(isSubmitting = false) }
                sendEvent(
                    DepositEvent.ShowError("Deposit failed")
                )
            }
        }

        return copy(isSubmitting = true)
    }
}