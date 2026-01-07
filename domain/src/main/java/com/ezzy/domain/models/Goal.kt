package com.ezzy.domain.models

import com.ezzy.domain.enums.GoalCategory
import java.time.LocalDate

data class Goal(
    val id: Long,
    val name: String,
    val category: GoalCategory,
    val targetAmount: Double,
    val totalSaved: Double,
    val progressPercent: Int,
    val targetDate: LocalDate,
    val isCompleted: Boolean
)