package com.ezzy.presentation.features.goal.withdraw.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.ezzy.domain.enums.GoalStatus
import com.ezzy.domain.repository.ContributionRepository
import com.ezzy.domain.repository.GoalRepository
import com.ezzy.presentation.features.goal.withdraw.actions.WithdrawAction
import com.ezzy.presentation.features.goal.withdraw.enums.WithdrawDestination
import com.ezzy.presentation.features.goal.withdraw.events.WithdrawEvent
import com.ezzy.presentation.features.goal.withdraw.state.WithdrawState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WithdrawViewModel @Inject constructor(
    private val goalRepository: GoalRepository,
    private val contributionRepository: ContributionRepository,
    savedStateHandle: SavedStateHandle
) : BaseMviViewModel<
        WithdrawState,
        WithdrawAction,
        WithdrawEvent
        >(WithdrawState()) {

    private val initialGoalId: Long =
        savedStateHandle["goalId"]
            ?: error("goalId is required for WithdrawRoute")

    init {
        dispatch(WithdrawAction.LoadGoals)
        registerReducers()
        dispatch(
            WithdrawAction.OnGoalSelected(initialGoalId)
        )
    }

    private fun registerReducers() {
        registerReducer(WithdrawAction.OnGoalSelected::class) { action ->
            copy(selectedGoalId = action.goalId)
        }

        registerReducer(WithdrawAction.OnWithdrawDestinationSelected::class) { action ->
            copy(withdrawTo = action.destination)
        }

        registerReducer(WithdrawAction.OnPhoneNumberChange::class) { action ->
            copy(phoneNumber = action.value)
        }

        registerReducer(WithdrawAction.OnAmountChange::class) { action ->
            copy(amount = action.value)
        }


        registerReducer(WithdrawAction.SubmitWithdraw::class) {
            if (!isValid()) {
                sendEvent(
                    WithdrawEvent.ShowError("Invalid withdraw details")
                )
                this
            } else {
                performWithdraw()
            }
        }

        registerReducer(WithdrawAction.DismissSuccessDialog::class) {
            copy(showSuccessDialog = false)
        }

        registerReducer(WithdrawAction.GoToMyGoals::class) {
            sendEvent(WithdrawEvent.NavigateToGoals)
            copy(showSuccessDialog = false)
        }
    }


    override fun onUnhandledAction(action: WithdrawAction) {
        when (action) {
            WithdrawAction.LoadGoals -> loadGoals()
            WithdrawAction.NavigateBack ->
                sendEvent(WithdrawEvent.NavigateBack)

            else -> Unit
        }
    }


    private fun loadGoals() {
        viewModelScope.launch {
            goalRepository.getGoals(GoalStatus.ACTIVE).collect { goals ->
                reduce {
                    copy(
                        goals = goals,
                        selectedGoalId =
                            selectedGoalId ?: goals.firstOrNull()?.id
                    )
                }
            }
        }
    }


    private fun WithdrawState.isValid(): Boolean =
        selectedGoal != null &&
                amount.toDoubleOrNull()?.let {
                    it > 0 && it <= availableBalance
                } == true &&
                (withdrawTo != WithdrawDestination.MPESA || phoneNumber.length >= 9)

    private fun WithdrawState.performWithdraw(): WithdrawState {

        viewModelScope.launch {
            try {
                contributionRepository.addContribution(
                    goalId = selectedGoalId!!,
                    amount = -amount.toDouble() // withdrawal
                )

                reduce {
                    copy(
                        isProcessing = false,
                        showSuccessDialog = true,
                        withdrawnAmount = amount.toDouble()
                    )
                }

            } catch (t: Throwable) {
                reduce { copy(isProcessing = false) }
                sendEvent(
                    WithdrawEvent.ShowError("Withdraw failed")
                )
            }
        }

        return copy(isProcessing = true)
    }
}