package com.ezzy.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ezzy.domain.enums.GoalCategory
import com.ezzy.domain.enums.GoalStatus

@Entity(tableName = "goals")
data class GoalEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val targetAmount: Double,
    val targetDate: Long?,
    val createdAt: Long = System.currentTimeMillis(),
    val status: GoalStatus = GoalStatus.ACTIVE,
    val category: GoalCategory,
)