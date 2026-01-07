package com.ezzy.domain.models

data class Goal(
    val id: Long,
    val name: String,
    val targetAmount: Double,
    val totalSaved: Double,
    val progressPercent: Int,
    val isCompleted: Boolean
)