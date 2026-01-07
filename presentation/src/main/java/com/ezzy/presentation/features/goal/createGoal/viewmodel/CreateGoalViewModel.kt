package com.ezzy.presentation.features.goal.createGoal.viewmodel

import androidx.lifecycle.viewModelScope
import com.ezzy.domain.models.Goal
import com.ezzy.domain.repository.GoalRepository
import com.ezzy.presentation.features.goal.createGoal.actions.CreateGoalAction
import com.ezzy.presentation.features.goal.createGoal.events.CreateGoalEvent
import com.ezzy.presentation.features.goal.createGoal.state.CreateGoalState
import com.ezzy.presentation.mviSetUp.viewModel.BaseMviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateGoalViewModel @Inject constructor(
    private val goalRepository: GoalRepository
) : BaseMviViewModel<
        CreateGoalState,
        CreateGoalAction,
        CreateGoalEvent
        >(CreateGoalState()) {

    init {
        registerReducers()
    }

    private fun registerReducers() {

        registerReducer(CreateGoalAction.OnNameChange::class) { action ->
            copy(name = action.value)
        }

        registerReducer(CreateGoalAction.OnCategorySelected::class) { action ->
            copy(category = action.category)
        }

        registerReducer(CreateGoalAction.OnTargetAmountChange::class) { action ->
            copy(targetAmount = action.value)
        }

        registerReducer(CreateGoalAction.OnDateClicked::class) {
            copy(showDatePicker = true)
        }

        registerReducer(CreateGoalAction.OnDateSelected::class) { action ->
            copy(
                targetDate = action.date,
                showDatePicker = false
            )
        }

        registerReducer(CreateGoalAction.DismissDatePicker::class) {
            copy(showDatePicker = false)
        }

        registerReducer(CreateGoalAction.Submit::class) {
            if (!isValid()) {
                sendEvent(
                    CreateGoalEvent.ShowError(
                        "Please fill all required fields"
                    )
                )
                this
            } else {
                saveGoal()
            }
        }

        registerReducer(CreateGoalAction.DismissSuccessDialog::class) {
            copy(showSuccessDialog = false)
        }

        registerReducer(CreateGoalAction.GoToMyGoals::class) {
            sendEvent(CreateGoalEvent.NavigateToMyGoals)
            copy(showSuccessDialog = false)
        }
    }

    override fun onUnhandledAction(action: CreateGoalAction) {
        when (action) {
            CreateGoalAction.NavigateBack -> {
                sendEvent(CreateGoalEvent.NavigateBack)
            }

            else -> super.onUnhandledAction(action)
        }

    }

    private fun CreateGoalState.isValid(): Boolean =
        name.isNotBlank() &&
                category != null &&
                targetAmount.toDoubleOrNull()?.let { it > 0 } == true &&
                targetDate != null

    private fun CreateGoalState.saveGoal(): CreateGoalState {

        viewModelScope.launch {
            try {
                val goalId = goalRepository.addGoal(
                    Goal(
                        id = 0L,
                        name = name,
                        category = category!!,
                        targetAmount = targetAmount.toDouble(),
                        totalSaved = 0.0,
                        progressPercent = 0,
                        targetDate = targetDate!!,
                        isCompleted = false
                    )
                )

                copy(
                    isSaving = false,
                    showSuccessDialog = true,
                    createdGoalId = goalId
                )

            } catch (t: Throwable) {
                sendEvent(
                    CreateGoalEvent.ShowError(
                        "Failed to create goal"
                    )
                )

                copy(isSaving = false)
            }
        }

        return copy(isSaving = true)
    }
}