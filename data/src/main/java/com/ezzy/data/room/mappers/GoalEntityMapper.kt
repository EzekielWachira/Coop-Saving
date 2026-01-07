package com.ezzy.data.room.mappers

import android.R.attr.category
import com.ezzy.data.room.entities.GoalEntity
import com.ezzy.domain.enums.GoalStatus
import com.ezzy.domain.models.Goal
import java.time.Instant
import java.time.ZoneId

fun GoalEntity.toDomain(totalSaved: Double): Goal {
    val progress = if (targetAmount > 0) {
        ((totalSaved / targetAmount) * 100)
            .coerceAtMost(100.0)
            .toInt()
    } else {
        0
    }

    return Goal(
        id = id,
        name = name,
        category = category,
        targetAmount = targetAmount,
        totalSaved = totalSaved,
        progressPercent = progress,
        targetDate = Instant.ofEpochMilli(targetDate ?: System.currentTimeMillis())
            .atZone(ZoneId.systemDefault())
            .toLocalDate(),
        isCompleted = status == GoalStatus.COMPLETED ||
                totalSaved >= targetAmount
    )
}

fun Goal.toEntity(): GoalEntity =
    GoalEntity(
        id = id,
        name = name,
        category = category,
        targetAmount = targetAmount,
        targetDate = targetDate
            .atStartOfDay(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli(),
        status = if (isCompleted) {
            GoalStatus.COMPLETED
        } else {
            GoalStatus.ACTIVE
        }
    )