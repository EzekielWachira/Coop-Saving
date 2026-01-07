package com.ezzy.data.room.mappers

import com.ezzy.data.room.entities.GoalEntity
import com.ezzy.domain.models.Goal

fun GoalEntity.toDomain(totalSaved: Double): Goal {
    val progress = ((totalSaved / targetAmount) * 100).toInt()
    return Goal(
        id = id,
        name = name,
        targetAmount = targetAmount,
        totalSaved = totalSaved,
        progressPercent = progress,
        isCompleted = totalSaved >= targetAmount
    )
}


fun Goal.toEntity(): GoalEntity =
    GoalEntity(
        id = id,
        name = name,
        targetAmount = targetAmount,
        targetDate = null
    )