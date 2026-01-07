package com.ezzy.presentation.features.goal.createGoal.state

import com.ezzy.domain.enums.GoalCategory
import com.ezzy.presentation.mviSetUp.MviState
import java.time.LocalDate

data class CreateGoalState(
    val name: String = "",
    val category: GoalCategory? = null,
    val targetAmount: String = "",
    val targetDate: LocalDate? = null,

    val isSaving: Boolean = false,
    val showSuccessDialog: Boolean = false,
    val createdGoalId: Long? = null,
    val showDatePicker: Boolean = false,
) : MviState